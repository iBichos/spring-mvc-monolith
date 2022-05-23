package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.ProductDAO;
import ibichos.foundation.monolith.model.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {
    private final ProductDAO productDAO;

    public CartService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getProductById(UUID id) {
        return productDAO.selectById(id);
    }
}
