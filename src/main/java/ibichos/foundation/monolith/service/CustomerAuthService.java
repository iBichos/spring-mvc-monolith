package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.CustomerDAO;
import ibichos.foundation.monolith.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthService {

    private final CustomerDAO customerDAO;

    public CustomerAuthService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    public boolean login(String email, String password) {
        Customer customer = customerDAO.selectByEmail(email).get();
        return customer.getPassword().equals(password);
    }
}