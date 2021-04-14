package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.domain.Collection;
import top.crwenassert.favorites.domain.Favorites;
import top.crwenassert.favorites.eumn.CollectionEnum;
import top.crwenassert.favorites.eumn.FavoritesEnum;
import top.crwenassert.favorites.repository.CollectionRepository;
import top.crwenassert.favorites.repository.FavoritesRepository;
import top.crwenassert.favorites.service.FavoritesService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: FavoritesServiceImpl
 * Description:
 * date: 2020/7/27 20:57
 *
 * @author crwen
 * @create 2020-07-27-20:57
 * @since JDK 1.8
 */
@Service
@Slf4j
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    /**
     *  创建收藏夹
     * @param favorites
     * @return
     */
    @Override
    public Favorites save(Favorites favorites) {
        if (favorites != null) {

        }
        Favorites save = favoritesRepository.save(favorites);
        log.info("【新建收藏夹】", save.getName());
        return save;
    }

    /**
     *  根据 id 删除收藏夹
     * @param id
     */
    @Override
    public void delete(Long id) {
        // 查找出收藏夹下所有的收藏
        List<Collection> collectionList = collectionRepository.getByFavoriteId(id);
        // 删除收藏
        collectionRepository.deleteInBatch(collectionList);
        favoritesRepository.deleteById(id);
        log.info("【删除收藏夹】");
    }

    @Override
    public Favorites getFavoritesById(Long id) {
        Favorites favorites = favoritesRepository.getById(id);
        return favorites;
    }

    @Override
    public List<Favorites> getFavoritesByUser(Long userId) {
        List<Favorites> favoritesList = favoritesRepository.getByUserId(userId);

        return favoritesList;
    }

    @Override
    public List<Favorites> getSubFavorites(Long parentId) {
        List<Favorites> favoritesList = favoritesRepository.getByParentId(parentId);
        return favoritesList;
    }

    @Override
    public Favorites getByUserIdAndName(Long userId, String name) {
        //if (name == null || name.length() == 0) {
        //
        //    throw new IllegalArgumentException();
        //}
        Favorites favorites = favoritesRepository.getByUserIdAndName(userId, name);
        return favorites;
    }

    /**
     *  批量删除收藏夹
     //favoritesRepository.getByU
     * @param favoritesList
     */
    @Override
    public void deleteInBatch(List<Favorites> favoritesList) {
        // 获取要删除收藏夹的 下的收藏
        Set<Collection> updateCollectionSet = new HashSet<>();
        Set<Favorites> updateFavoritesSet = new HashSet<>();

        for (Favorites favorites : favoritesList) {
            favorites.setStatus(FavoritesEnum.DELETED.getStatus());
            updateFavoritesSet.add(favorites);
            // 获取收藏夹下所有收藏
            List<Collection> collectionList = collectionRepository.getByFavoriteId(favorites.getId());
            for (Collection collection : collectionList) {
                collection.setStatus(CollectionEnum.DELETED.getStatus());
                updateCollectionSet.add(collection);
            }
        }

        // 更新收藏
        collectionRepository.saveAll(updateCollectionSet);
        // 更新收藏夹
        favoritesRepository.saveAll(updateFavoritesSet);
        log.info("【删除收藏夹】 " + favoritesList);
        //collectionRepository.saveAll(collectionSet);
        // 删除 收藏
        //collectionRepository.deleteInBatch(collectionSet);
        // 删除收藏夹
        //favoritesRepository.deleteInBatch(favoritesList);
    }

    /**
     *  更新收藏夹
     * @param favorites
     * @return
     */
    @Override
    public Favorites update(Favorites favorites) {
        //Favorites favorites = favoritesRepository.getById(favorites.getId());

        Favorites update = null;
        if (favorites != null) {
            update = favoritesRepository.save(favorites);
        }
        return update;
    }
}
