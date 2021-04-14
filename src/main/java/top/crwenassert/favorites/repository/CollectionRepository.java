package top.crwenassert.favorites.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import top.crwenassert.favorites.domain.Collection;

import java.util.List;

/**
 * ClassName: CollectionRepository
 * Description:
 * date: 2020/7/26 23:59
 *
 * @author crwen
 * @create 2020-07-26-23:59
 * @since JDK 1.8
 */
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Collection getById(Long id);

    List<Collection> getByFavoriteId(Long id);

    Page<Collection> getAllByFavoriteId(Long favoriteId, Pageable pageable);
    Page<Collection> getAllByFavoriteIdAndStatus(Long favoriteId, int status, Pageable pageable);

}
