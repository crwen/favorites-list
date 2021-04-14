package top.crwenassert.favorites.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.crwenassert.favorites.domain.Favorites;

import java.util.List;

/**
 * ClassName: FavoritesRepositoryTest
 * Description:
 * date: 2020/7/28 7:54
 *
 * @author crwen
 * @create 2020-07-28-7:54
 * @since JDK 1.8
 */
@SpringBootTest
class FavoritesRepositoryTest {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Test
    void getByUserId() {
        List<Favorites> favoritesList = favoritesRepository.getByUserId(1L);
        Assertions.assertNotNull(favoritesList);
        //Assertions.assertNotEquals(0, favoritesList.size());
        favoritesList.forEach(favorites -> {
            System.out.println(favorites);
        });
    }

    @Test
    void getByParentId() {
    }
}