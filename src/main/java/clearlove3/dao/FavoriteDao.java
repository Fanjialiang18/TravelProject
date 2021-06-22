package clearlove3.dao;

import clearlove3.domain.Favorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FavoriteDao {
    @Select("select * from tab_favorite where rid = #{rid} and uid = #{uid}")
    Favorite isFavorite(@Param("uid") int uid, @Param("rid") int rid);
    @Select("select count(*) from tab_favorite where rid = #{rid}")
    int findCountByRid(int rid);
    @Insert("insert into tab_favorite values(#{rid},#{date},#{uid})")
    int addFavorite(@Param("uid") int uid,@Param("rid") int rid,@Param("date") Date date);
    @Select("select rid from tab_favorite where uid = #{uid} limit #{start} , #{pageSize}")
    List<Integer> findFavoriteByPage(@Param("uid") int uid,@Param("start") int start,@Param("pageSize") int pageSize);
    @Select("select count(*) from tab_favorite where uid = #{uid}")
    int findAllFavorite(int uid);
}
