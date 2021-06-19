package clearlove3.service.impl;

import clearlove3.dao.CategoryDao;
import clearlove3.domain.Category;
import clearlove3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao;
    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Category> findall() {
        return dao.findAll();
    }
}
