package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.CustomersDAO;
import ibichos.foundation.monolith.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomersDAO customersDAO;

    public Customer createCustomer(Customer customer) {
        return customersDAO.insert(customer);
    }

    public Customer getCustomer(String email, String password) {
        return customersDAO.selectByEmailAndPassword(email, password).orElse(null);
    }
}