package top.crwenassert.favorites.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.crwenassert.favorites.domain.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ClassName: UserRepositoryTest
 * Description:
 * date: 2020/7/21 17:20
 *
 * @author crwen
 * @create 2020-07-21-17:20
 * @since JDK 1.8
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("crwen");
        user.setPassword("123456");
        user.setTelephone("123456");
        user.setEmail("123456@qq.com");
        User save = userRepository.save(user);
        assertNotNull(save);
        System.out.println(save);
    }

    @Test
    void updateTest() {
        User user = userRepository.findById(1L).get();
        assertNotNull(user);
        Assertions.assertEquals(1L, user.getId());
    }

}