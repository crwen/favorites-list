package top.crwenassert.favorites.service;

import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.dto.FormDTO;

import java.util.List;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/7/21 17:33
 *
 * @author crwen
 * @create 2020-07-21-17:33
 * @since JDK 1.8
 */
public interface UserService {

    List<User> getAll();

    User save(FormDTO form);

    User getUserById(Long id);

    User getUserByPhone(String phone);

    User  getUserByEmail(String email);

    User getUserByUsername(String username);

    List<User> getUserByUsernameOrTelephoneOrEmail(String username, String phone, String email);

}
