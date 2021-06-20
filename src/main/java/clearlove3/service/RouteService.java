package clearlove3.service;

import clearlove3.domain.PageBean;
import clearlove3.domain.Route;

public interface RouteService {
    /**
     * 根据路线id分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> queryByPage(int cid, int currentPage, int pageSize);
}