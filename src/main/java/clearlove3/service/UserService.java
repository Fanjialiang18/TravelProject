package clearlove3.service;

import clearlove3.domain.User;

public interface UserService {
    /**
     * 用户注册，返回是否成功
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 用户激活，返回是否成功
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return 返回登陆成功的用户信息
     */
    User login(User user);
}
