package clearlove3.service.impl;

import clearlove3.dao.FavoriteDao;
import clearlove3.domain.Favorite;
import clearlove3.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDao favoriteDao;
    @Autowired
    public FavoriteServiceImpl(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @Override
    public boolean isFavorite(int uid, int rid) {
        Favorite favorite = favoriteDao.isFavorite(uid, rid);
        System.out.println(favorite);
        return favorite!=null;
    }

    @Override
    public int addFavorite(int uid, int rid,Date date) {
        return favoriteDao.addFavorite(uid,rid,date);
    }
}
