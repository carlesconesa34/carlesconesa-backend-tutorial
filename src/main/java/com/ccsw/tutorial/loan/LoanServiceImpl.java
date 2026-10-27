package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.customer.CustomerRepository;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.game.GameRepository;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        Specification<Loan> spec = Specification
                .where(new LoanSpecification(new SearchCriteria("game.id" , ":", dto.getGameId())))
                .and(new LoanSpecification(new SearchCriteria("customer.id", ":", dto.getCustomerId())))
                .and(new LoanSpecification(new SearchCriteria("date", ":", dto.getDate())));


        Pageable pageable = dto.getPageable().getPageable();

        return this.loanRepository.findAll(spec, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LoanDto dto) {

        Loan loan;

        if (id == null) {
            loan = new Loan();
        } else {
            loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el préstamo con id" + id));
        }

        BeanUtils.copyProperties(dto, loan, "id", "game", "customer");

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        LocalDate day = dto.getStartDate();

        if(dto.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException(
                    "La fecha de inicio debe ser igual o posterior al día actual."
            );
        }

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        if (days + 1 > 14) {
            throw new IllegalArgumentException("Loan cannot exceed 14 days");
        }

        List<Loan> gameLoans = loanRepository.findByGameId(dto.getGame().getId());
        for (Loan existing : gameLoans) {

            if (id != null && existing.getId().equals(id)) {
                continue;
            }

            boolean overlap = !dto.getEndDate().isBefore(existing.getStartDate()) && !dto.getStartDate().isAfter(existing.getEndDate());

            if (overlap) {
                throw new RuntimeException("El juego ya está prestado durante este periodo");
            }
        }

        List<Loan> customerLoans = loanRepository.findByCustomerId(dto.getCustomer().getId());
        while (!day.isAfter(dto.getEndDate())) {

            int activeLoans = 0;

            for (Loan existing : customerLoans) {

                if (id != null && existing.getId().equals(id)) {
                    continue;
                }

                boolean active = !day.isBefore(existing.getStartDate()) && !day.isAfter(existing.getEndDate());

                if (active) {
                    activeLoans++;
                }
            }

            if (activeLoans >= 2) {
                throw new RuntimeException("El cliente ya tiene dos préstamos activos  en una fecha dentro del periodo");
            }

            day = day.plusDays(1);
        }

        Customer customer = customerRepository.findById(dto.getCustomer().getId()).orElseThrow(() -> new RuntimeException("No existe el cliente seleccionado."));
        Game game = gameRepository.findById(dto.getGame().getId()).orElseThrow(() -> new RuntimeException("No existe el juego seleccionado."));

        loan.setCustomer(customer);
        loan.setGame(game);

        loan.setStartDate(dto.getStartDate());
        loan.setEndDate(dto.getEndDate());

        this.loanRepository.save(loan);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }
}