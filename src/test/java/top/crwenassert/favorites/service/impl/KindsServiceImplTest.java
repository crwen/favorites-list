package top.crwenassert.favorites.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.crwenassert.favorites.domain.Kinds;
import top.crwenassert.favorites.service.KindsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: KindsServiceImplTest
 * Description:
 * date: 2020/8/21 23:37
 *
 * @author crwen
 * @create 2020-08-21-23:37
 * @since JDK 1.8
 */
@SpringBootTest
class KindsServiceImplTest {

    @Autowired
    private KindsService kindsService;

    @Test
    void getKindsByUser() {
        List<Kinds> kindsList = kindsService.getKindsByUser(1L, 1, 10);
        System.out.println(kindsList);
    }
}