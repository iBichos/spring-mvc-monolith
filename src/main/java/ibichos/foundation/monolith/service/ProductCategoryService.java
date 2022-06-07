package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.ProductCategoriesDAO;
import ibichos.foundation.monolith.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoriesDAO productCategoriesDAO;

    public List<UUID> getCategoriesIds(UUID productId) {
        return productCategoriesDAO.selectByProductId(productId).stream().map(ProductCategory::getCategoryId).collect(Collectors.toList());
    }

    public List<UUID> getProductsIds(UUID categoryId) {
        return productCategoriesDAO.selectByCategoryId(categoryId).stream().map(ProductCategory::getProductId).collect(Collectors.toList());
    }
}
