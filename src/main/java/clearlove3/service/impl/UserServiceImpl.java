package clearlove3.service.impl;

import clearlove3.dao.UserDao;
import clearlove3.domain.User;
import clearlove3.service.UserService;
import clearlove3.util.MailUtils;
import clearlove3.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean register(User user) {
        User result = dao.searchUser(user.getUsername());
        if(result!=null){
            //若数据库查询到用户说明已经存在，注册失败
            return false;
        }else {
            //注册成功，生成激活邮件
            //生成激活码
            String uuid = UuidUtil.getUuid();
            //设置激活码
            user.setCode(uuid);
            //设置激活状态
            user.setStatus("N");
            String text="<a href='http://localhost:80/travel/user/user_active?code="+user.getCode()+"'>点击激活账号</a>";
            MailUtils.sendMail(user.getEmail(),text,"激活账号");
            dao.save(user);
            return true;
        }
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //查询激活码是否正确然后激活
        User user = dao.searchUserByCode(code);
        if(user!=null){
            dao.updateStatus(user);
            return true;
        }else
            return false;
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }
}
