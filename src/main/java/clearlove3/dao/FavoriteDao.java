package clearlove3.dao;

import clearlove3.domain.Favorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FavoriteDao {
    @Select("select * from tab_favorite where rid = #{rid} and uid = #{uid}")
    Favorite isFavorite(@Param("uid") int uid, @Param("rid") int rid);
    @Select("select count(*) from tab_favorite where rid = #{rid}")
    int findCountByRid(int rid);
    @Insert("insert into tab_favorite values(#{rid},#{date},#{uid})")
    int addFavorite(@Param("uid") int uid,@Param("rid") int rid,@Param("date") Date date);
}
