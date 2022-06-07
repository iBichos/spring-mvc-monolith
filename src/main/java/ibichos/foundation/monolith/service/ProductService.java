package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.ProductsDAO;
import ibichos.foundation.monolith.model.Merchant;
import ibichos.foundation.monolith.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductsDAO productsDAO;

    public List<Product> getAllProducts() {
        return productsDAO.select();
    }

    public Product getProduct(UUID productId) {
        return productsDAO.selectById(productId).orElseThrow();
    }

    public List<Product> getProducts(List<UUID> productsIds) {
        return productsDAO.selectByIds(productsIds);
    }

    public Product getDummyProduct() {
        return Product.builder().name("Nome do produto").description("Descrição do produto").price(BigDecimal.ZERO).amountInStock(0).build();
    }

    public void createProduct(Product product) {
        productsDAO.insert(product);
    }

    public void updateProduct(Product product) {
        productsDAO.update(product);
    }

    public void deleteProduct(Product product) {
        productsDAO.delete(product);
    }

    public List<Product> getProductsByMerchantId(UUID merchantId) {
        return productsDAO.selectByMerchantId(merchantId);
    }
}
