package top.crwenassert.favorites.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.service.UserService;

/**
 * ClassName: UserServiceImplTest
 * Description:
 * date: 2020/7/21 17:38
 *
 * @author crwen
 * @create 2020-07-21-17:38
 * @since JDK 1.8
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void getUserById() {
        User user = userService.getUserById(1L);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(1, user.getId());
        System.out.println(user);
    }

    @Test
    public void test() {
        System.out.println(redisTemplate);
    }
}