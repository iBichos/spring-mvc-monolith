package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.ProductDAO;
import ibichos.foundation.monolith.model.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogService {

    private final ProductDAO productDAO;

    public ProductCatalogService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public List<Product> getProductList() {
        return productDAO.select();
    }

    public List<Product> getProductList(String category) {
        return productDAO.select(category);
    }
}
