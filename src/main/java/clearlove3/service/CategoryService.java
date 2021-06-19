package clearlove3.service;

import clearlove3.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有Category
     * @return
     */
    public List<Category> findall();
}
