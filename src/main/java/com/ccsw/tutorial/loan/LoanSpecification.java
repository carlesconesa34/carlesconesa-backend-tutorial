package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanSpecification {

    public static Specification<Loan> search(Long gameId, Long clientId, LocalDate date) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (gameId != null) {
                predicates.add(builder.equal(root.get("game").get("id"), gameId));
            }

            if (clientId != null) {
                predicates.add(builder.equal(root.get("client").get("id"), clientId));
            }

            if (date != null) {

                predicates.add(builder.lessThanOrEqualTo(root.get("startDate"), date));

                predicates.add(builder.greaterThanOrEqualTo(root.get("endDate"), date));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}