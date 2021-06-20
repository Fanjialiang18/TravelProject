package clearlove3.controller;

import clearlove3.domain.ResultInfo;
import clearlove3.domain.User;
import clearlove3.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;
    private ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(path = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断验证码是否正确
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //移除验证码，避免复用
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误!");
            response.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(response.getWriter(),resultInfo);
            return;
        }
        //首先获取用户名和数据
        String username = request.getParameter("username");
        String passwd = request.getParameter("password");
        User u=new User();
        u.setUsername(username);
        u.setPassword(passwd);
        u = userService.login(u);
        ResultInfo info=new ResultInfo();
        if(u!=null){
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }else{
            //登陆失败
            info.setFlag(false);
            info.setErrorMsg("密码或者账号不正确!");
        }
        String json=objectMapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    @RequestMapping(path = "/active")
    public void user_active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String msg = null;
        if (code != null) {
            boolean flag = userService.active(code);
            if (flag) {
                msg = "激活成功，请<a href='login.html'>登陆</a>";
            } else {
                msg = "激活失败!请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    @RequestMapping(path = "/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    @RequestMapping(path = "/findUser")
    public void findUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中获取
        Object user = req.getSession().getAttribute("user");
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getOutputStream(),user);
    }
    @RequestMapping(path = "/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证码是否正确
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //移除验证码，避免复用
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            //注册失败
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误!");
            response.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(response.getWriter(),resultInfo);
            return;
        }
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        //封装对象
        try {
            BeanUtils.populate(user,parameterMap);
            System.out.println("成功调用beanUtils方法");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service层完成注册
        boolean flag=userService.regist(user);
        ResultInfo info=new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将info序列化为json
        String json=objectMapper.writeValueAsString(info);
        //将数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
