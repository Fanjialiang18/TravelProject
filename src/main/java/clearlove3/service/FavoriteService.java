package clearlove3.service;

import java.util.Date;
import java.util.List;

public interface FavoriteService {
    boolean isFavorite(int uid,int rid);

    int addFavorite(int uid, int rid, Date date);
}
