package ibichos.foundation.monolith.service;

import ibichos.foundation.monolith.dao.CategoriesDAO;
import ibichos.foundation.monolith.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoriesDAO categoriesDAO;

    public Category getCategory(UUID categoryId) {
        Optional<Category> category = categoriesDAO.selectById(categoryId);
        return category.orElse(null);
    }

    public Category getCategory(String name) {
        Optional<Category> category = categoriesDAO.selectByName(name);
        return category.orElse(null);
    }

    public List<Category> getCategories(List<UUID> categoriesIds) {
        return categoriesDAO.selectByIds(categoriesIds);
    }

    public List<UUID> getProductsIds(UUID categoryId) {
        return categoriesDAO.aggregateProductsIds(categoryId);
    }
}