package clearlove3.service.impl;

import clearlove3.dao.CategoryDao;
import clearlove3.domain.Category;
import clearlove3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao;
    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }
}
