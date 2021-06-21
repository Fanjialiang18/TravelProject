package clearlove3.controller;

import clearlove3.domain.PageBean;
import clearlove3.domain.Route;
import clearlove3.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(path = "/route")
public class RouteController {
    private final RouteService routeService;
    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(path = "/queryByPage")
    @ResponseBody
    public PageBean<Route> queryByPage(HttpServletRequest request){
        //首先从request中获取参数
        String rname = request.getParameter("rname");
        String currentPageStr = request.getParameter("currentPage");
        System.out.println(currentPageStr);
        String pageSizeStr = request.getParameter("pageSize");
        System.out.println(pageSizeStr);
        String cidStr = request.getParameter("cid");
        System.out.println(cidStr);
        int cid=5;
        int pageSize=5;
        int currentPage=1;
        if(cidStr!=null&&cidStr.length()!=0){
            cid = Integer.parseInt(cidStr);
        }
        if(pageSizeStr!=null&&pageSizeStr.length()!=0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        if(currentPageStr!=null&&currentPageStr.length()!=0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        //调用service查询PageBean对象
        PageBean<Route> routePageBean = routeService.queryByPage(cid, currentPage, pageSize);
        for(Route r : routePageBean.getList()){
            System.out.println(r);
        }
        return routePageBean;
        //将查询到的对象序列化并且返回到网页
    }

    /**
     * 根据rid查询一个路线
     * @param request
     */
    @RequestMapping(path = "/findOne")
    @ResponseBody
    public Route findOne(HttpServletRequest request){
        String rid = request.getParameter("rid");
        Route route=routeService.findOne(rid);
        System.out.println(route);
        return route;
    }
}
