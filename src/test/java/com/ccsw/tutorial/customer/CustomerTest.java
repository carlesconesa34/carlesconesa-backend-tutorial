package com.ccsw.tutorial.customer;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void findAllShouldReturnAllCustomers() {

        List<Customer> list = new ArrayList<>();
        list.add(mock(Customer.class));

        when(customerRepository.findAll()).thenReturn(list);
        List<Customer> clients = customerService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
    }

    public static final String CUSTOMER_NAME = "CLI1";

    @Test
    public void saveNotExistsCustomerIdShouldInsert() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(CUSTOMER_NAME);

        ArgumentCaptor<Customer> client = ArgumentCaptor.forClass(Customer.class);

        customerService.save(null, customerDto);

        verify(customerRepository).save(client.capture());

        assertEquals(CUSTOMER_NAME, client.getValue().getName());
    }

    public static final Long EXISTS_CLIENT_ID = 1L;

    @Test
    public void saveExistsCustomerIdShouldUpdate() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(CUSTOMER_NAME);

        Customer client = mock(Customer.class);
        when(customerRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        customerService.save(EXISTS_CLIENT_ID, customerDto);

        verify(customerRepository).save(client);
    }

    @Test
    public void deleteExistsCustomerIdShouldDelete() throws Exception {

        Customer client = mock(Customer.class);
        when(customerRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        customerService.delete(EXISTS_CLIENT_ID);

        verify(customerRepository).deleteById(EXISTS_CLIENT_ID);
    }
}

