package top.crwenassert.favorites.utils;

import top.crwenassert.favorites.domain.Collection;
import top.crwenassert.favorites.domain.Favorites;
import top.crwenassert.favorites.vo.CollectionVO;
import top.crwenassert.favorites.vo.FavoritesVO;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ConvertUtil
 * Description:
 * date: 2020/8/13 16:02
 *
 * @author crwen
 * @create 2020-08-13-16:02
 * @since JDK 1.8
 */
public class ConvertUtil {

    public static FavoritesVO convertFavorites2FavoritesVO(Favorites favorites) {
        FavoritesVO favoritesVO = new FavoritesVO();
        favoritesVO.setId(favorites.getId());
        favoritesVO.setName(favorites.getName());
        favoritesVO.setParentId(favorites.getParentId());
        return favoritesVO;
    }

    public static List<FavoritesVO> convertFavorites2FavoritesVO(List<Favorites> favoritesList) {
        List<FavoritesVO> favoritesVOList = new ArrayList<>();
        for (Favorites favorites : favoritesList) {
            favoritesVOList.add(convertFavorites2FavoritesVO(favorites));
        }
        return favoritesVOList;
    }

    /**
     *  将 Collection 转换为 CollectionVO
     * @param collection
     * @return
     */
    public static CollectionVO convertCollection2CollectionVO(Collection collection) {
        CollectionVO collectionVO = new CollectionVO();
        collectionVO.setId(collection.getId());
        collectionVO.setFavoriteId(collection.getFavoriteId());
        collectionVO.setTitle(collection.getTitle());
        collectionVO.setUrl(collection.getUrl());
        collectionVO.setStatus(collection.getStatus());
        collection.setUpdateTime(collection.getUpdateTime());
        return collectionVO;
    }

    public static List<CollectionVO> convertCollection2CollectionVO(List<Collection> collectionList) {
        List<CollectionVO> collectionVOList = new ArrayList<>();
        for (Collection collection : collectionList) {
            collectionVOList.add(convertCollection2CollectionVO(collection));
        }
        return collectionVOList;
    }

    public static Collection convertCollectionVO2Collection(CollectionVO collectionVO) {
        Collection collection = new Collection();
        collection.setId(collectionVO.getId());
        collection.setFavoriteId(collectionVO.getFavoriteId());
        collection.setTitle(collectionVO.getTitle());
        collection.setUrl(collectionVO.getUrl());
        collection.setStatus(collectionVO.getStatus());
        collection.setUpdateTime(collectionVO.getUpdateTime());
        return collection;
    }

    public static List<Collection> convertCollectionVO2Collection(List<CollectionVO> collectionVOList) {
        List<Collection> collectionList = new ArrayList<>();
        for (CollectionVO collectionVO : collectionVOList) {
            collectionList.add(convertCollectionVO2Collection(collectionVO));
        }
        return collectionList;
    }

}
