package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.model.Account;

import ibichos.foundation.monolith.model.Customer;
import ibichos.foundation.monolith.model.Merchant;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class AccountService {
    private Boolean isLoggedIn = false;
    private Boolean isCustomer = true;
    private Boolean isMerchant = false;
    private Account account;

    public Boolean login(Account account) {
        if (account != null) {
            this.account = account;
            isLoggedIn = true;
        }
        return isLoggedIn;
    }

    public Boolean logout() {
        isLoggedIn = false;
        return false;
    }

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Boolean validateEmail(String email, String emailRepeat) {
        return emailRepeat.equals(email);
    }

    public Boolean validatePassword(String password, String passwordRepeat) {
        return passwordRepeat.equals(password);
    }

    public void setIsCustomer(Boolean flag) {
        isCustomer = flag;
        isMerchant = !flag;
    }

    public void setIsMerchant(Boolean flag) {
        isCustomer = !flag;
        isMerchant = flag;
    }

    public Boolean isCustomer() {
        return isCustomer;
    }

    public Boolean isMerchant() {
        return isMerchant;
    }

    public Customer getCustomer() {
        return (Customer) account;
    }

    public Merchant getMerchant() {
        return (Merchant) account;
    }

    public Account getAccount() {
        return account;
    }
}
