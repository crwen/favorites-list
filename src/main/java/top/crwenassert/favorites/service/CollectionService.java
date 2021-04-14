package top.crwenassert.favorites.service;

import org.springframework.data.domain.Pageable;
import top.crwenassert.favorites.domain.Collection;
import top.crwenassert.favorites.vo.CollectionVO;

import java.util.List;

/**
 * ClassName: CollectionService
 * Description:
 * date: 2020/7/28 13:19
 *
 * @author crwen
 * @create 2020-07-28-13:19
 * @since JDK 1.8
 */
public interface CollectionService {
    Collection getById(Long id);

    //List<Collection> getByFavoriteId(Long id);

    Collection save(Collection collection);

    void delete(Long id);

    List<CollectionVO> getByFavoriteId(Long favoriteId, Pageable pageable);

}
