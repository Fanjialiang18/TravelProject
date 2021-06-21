package clearlove3.dao;

import clearlove3.domain.RouteImg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteImgDao {
    /**
     * 根据rid查询图片
     * @param rid
     * @return
     */
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg> findByRid(int rid);
}
