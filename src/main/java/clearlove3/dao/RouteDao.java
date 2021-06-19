package clearlove3.dao;

import clearlove3.domain.Route;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RouteDao {
    /**
     * 分页查询的Dao接口
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select * from tab_route where cid = #{cid} limit #{start} , #{pageSize}")
    public List<Route> queryByPage(int cid, int start, int pageSize);

    /**
     * 查询所有条数
     * @param cid
     * @return
     */
    @Select("select count(*) from tab_route where cid = #{cid}")
    public int queryTotal(int cid);
}
