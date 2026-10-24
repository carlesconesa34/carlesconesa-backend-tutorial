package com.ccsw.tutorial.customer;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     */
    public List<Customer> findAll() {
        return (List<Customer>) this.customerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void save(Long id, CustomerDto dto) {

        Customer existingCustomer = this.customerRepository.findCustomerByName(dto.getName());

        if (existingCustomer != null && (id == null || !existingCustomer.getId().equals(id))) {
            throw new RuntimeException("El cliente " + dto.getName() + " ya existe.");
        }
        Customer customer;

        if (id == null) {
            customer = new Customer();
        } else {
            customer = this.customerRepository.findById(id).orElse(null);
        }

        customer.setName(dto.getName());

        this.customerRepository.save(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) throws Exception {
        if(this.customerRepository.findById(id).orElse(null) == null){
            throw new Exception("Not exists");
        }

        this.customerRepository.deleteById(id);
    }

}
