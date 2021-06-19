package clearlove3.dao;

import clearlove3.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryDao {
    /**
     * 查询所有的分类
     * @return
     */
    @Select("select * from tab_category")
    public List<Category> findAll();
}
