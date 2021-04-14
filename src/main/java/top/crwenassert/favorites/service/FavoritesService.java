package top.crwenassert.favorites.service;

import top.crwenassert.favorites.domain.Favorites;

import java.util.List;

/**
 * ClassName: FavoritesService
 * Description:
 * date: 2020/7/27 20:55
 *
 * @author crwen
 * @create 2020-07-27-20:55
 * @since JDK 1.8
 */
public interface FavoritesService {
    /**
     *  新建收藏夹
     * @param favorites
     */
    Favorites save(Favorites favorites);

    void delete(Long id);

    /**
     *  通过 id 获取收藏夹
     * @param id
     * @return
     */
    Favorites getFavoritesById(Long id);

    /**
     *  通过用户获取收藏夹
     * @param id
     * @return
     */
    List<Favorites> getFavoritesByUser(Long id);

    /**
     *  通过父收藏夹 id 获取子收藏夹
     * @param parentId
     * @return
     */
    List<Favorites> getSubFavorites(Long parentId);

    Favorites getByUserIdAndName(Long useId, String name);

    void deleteInBatch(List<Favorites> favoritesList);

    //Favorites getByIdAndName(Long id, String name);

    Favorites update(Favorites favorites);
}
