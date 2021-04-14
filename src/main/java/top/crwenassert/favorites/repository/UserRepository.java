package top.crwenassert.favorites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.crwenassert.favorites.domain.User;

import java.util.List;

/**
 * ClassName: UserRepository
 * Description:
 * date: 2020/7/21 17:17
 *
 * @author crwen
 * @create 2020-07-21-17:17
 * @since JDK 1.8
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  根据 id 获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);


    User getUserByTelephone(String phone);

    User  getUserByEmail(String email);

    User getUserByUsername(String username);

    List<User> getUserByUsernameOrTelephoneOrEmail(String username, String phone, String email);

}
