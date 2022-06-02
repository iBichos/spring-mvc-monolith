package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.ProductsDAO;
import ibichos.foundation.monolith.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductsDAO productsDAO;

    public List<Product> getAllProducts() {
        return productsDAO.select();
    }

    public Product getProduct(UUID productId) {
        Optional<Product> product = productsDAO.selectById(productId);
        return product.orElse(null);
    }

    public List<Product> getProducts(List<UUID> productsIds) {
        return productsDAO.selectByIds(productsIds);
    }

    public List<UUID> getCategoriesIds(UUID productId) {
        return productsDAO.aggregateCategoriesIds(productId);
    }
}
