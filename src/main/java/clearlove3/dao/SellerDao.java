package clearlove3.dao;

import clearlove3.domain.Seller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao {
    /**
     * 根据sid查询商家信息
     * @param sid
     * @return
     */
    @Select("select * from tab_seller where sid = #{sid}")
    Seller findBySid(int sid);
}
