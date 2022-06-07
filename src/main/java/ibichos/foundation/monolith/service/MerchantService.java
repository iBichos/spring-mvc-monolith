package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.MerchantsDAO;
import ibichos.foundation.monolith.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {
    @Autowired
    private MerchantsDAO merchantsDAO;

    public Merchant getMerchant(UUID productId) {
        Optional<Merchant> merchant = merchantsDAO.selectById(productId);
        return merchant.orElse(null);
    }

    public Merchant getMerchant(String email, String password) {
        Optional<Merchant> merchant = merchantsDAO.selectByEmailAndPassword(email, password);
        return merchant.orElse(null);
    }

    public Merchant createMerchant(Merchant merchant) {
        return merchantsDAO.insert(merchant);
    }

    public void updateMerchant(Merchant merchant) {
        merchantsDAO.update(merchant);
    }
}
