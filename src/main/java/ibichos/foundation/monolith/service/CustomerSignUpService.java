package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.CustomerDAO;
import ibichos.foundation.monolith.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerSignUpService {

    private final CustomerDAO customerDAO;

    public CustomerSignUpService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public boolean validatePassword(String password, String passwordRepeat) {
        return passwordRepeat.equals(password);
    }

    public boolean validateEmail(String email, String emailRepeat) {
        return emailRepeat.equals(email);
    }

    public void createNewCustomer(String name, String email, String password, String cpf, String phone, String address) throws Exception {
        customerDAO.insert(Customer.builder()
                .name(name)
                .email(email)
                .cpf(cpf)
                .phone(phone)
                .password(password)
                .address(address)
                .build());
    }
}
