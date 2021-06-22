package clearlove3.service;

import java.util.Date;

public interface FavoriteService {
    boolean isFavorite(int uid,int rid);

    int addFavorite(int uid, int rid, Date date);
}
