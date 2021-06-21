package clearlove3.service.impl;

import clearlove3.dao.RouteDao;
import clearlove3.dao.RouteImgDao;
import clearlove3.dao.SellerDao;
import clearlove3.domain.PageBean;
import clearlove3.domain.Route;
import clearlove3.domain.RouteImg;
import clearlove3.domain.Seller;
import clearlove3.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("routeService")
public class RouteServiceImpl implements RouteService {
    private final RouteDao dao;
    private final RouteImgDao routeImgDao;
    private final SellerDao sellerDao;
    @Autowired
    public RouteServiceImpl(RouteDao dao, RouteImgDao routeImgDao,SellerDao sellerDao) {
        this.dao = dao;
        this.routeImgDao = routeImgDao;
        this.sellerDao=sellerDao;
    }

    @Override
    public Route findOne(String rid) {
        int num=Integer.parseInt(rid);
        Route one = dao.findOne(num);
        List<RouteImg> images = routeImgDao.findByRid(num);
        one.setRouteImgList(images);
        Seller seller = sellerDao.findBySid(one.getSid());
        one.setSeller(seller);
        System.out.println(one);
        return one;
    }

    @Override
    public PageBean<Route> queryByPage(int cid, int currentPage, int pageSize) {
        //首先查询所有的记录数
        int totalCount = dao.queryTotal(cid);
        //计算开始的页数
        int start = (currentPage - 1) * pageSize;
        //调用Dao层完成查询
        List<Route> routes = dao.queryByPage(cid, start, pageSize);
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
