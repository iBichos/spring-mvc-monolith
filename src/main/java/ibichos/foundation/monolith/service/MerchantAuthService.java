package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.MerchantDAO;
import ibichos.foundation.monolith.model.Customer;
import ibichos.foundation.monolith.model.Merchant;
import org.springframework.stereotype.Service;

@Service
public class MerchantAuthService {

    private final MerchantDAO merchantDAO;

    public MerchantAuthService(MerchantDAO merchantDAO) {
        this.merchantDAO = merchantDAO;
    }

    public boolean login(String email, String password) {
        Merchant merchant = merchantDAO.selectByEmail(email).get();
        return merchant.getPassword().equals(password);
    }
}
