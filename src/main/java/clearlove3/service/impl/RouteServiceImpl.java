package clearlove3.service.impl;

import clearlove3.dao.RouteDao;
import clearlove3.domain.PageBean;
import clearlove3.domain.Route;
import clearlove3.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao;
    @Autowired
    public RouteServiceImpl(RouteDao routeDao) {
        this.dao = routeDao;
    }

    @Override
    public PageBean<Route> queryByPage(int cid, int currentPage, int pageSize) {
        //首先查询所有的记录数
        int totalCount = dao.queryTotal(cid);
        //计算开始的页数
        int start = (currentPage - 1) * pageSize;
        //调用Dao层完成查询
        List<Route> routes = dao.queryByPage(cid, currentPage, pageSize);
        //创建一个PageBean并且为其设置值
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCid(cid);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(routes);
        pageBean.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1);
        return pageBean;
    }
}
