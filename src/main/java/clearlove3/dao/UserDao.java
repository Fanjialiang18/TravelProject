package clearlove3.dao;

import clearlove3.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 使用注解开发Dao
 */
@Repository
public interface UserDao {
    /**
     * 通过username查找user，返回值为一个List中的第一个元素
     * 因为确定返回值只有一个，所以不用List<User>来做返回值
     * @param username
     * @return
     */
    @Select("select * from tab_user where username = #{username}")
    public User searchUser(String username);
    @Select("select * from tab_user where code = #{code}")
    public User searchUserByCode(String code);
    @Insert("insert into tab_user(username,password,name,birthday,sex,telephone,email,code) values (#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email},#{code})")
    public boolean save(User user);
    @Update("update  tab_user set status = 'Y' where uid = #{uid}")
    public boolean updateStatus(User user);
    @Select("select * from tab_user where username = #{username} and password = #{password}")
    public User login(User user);
}
