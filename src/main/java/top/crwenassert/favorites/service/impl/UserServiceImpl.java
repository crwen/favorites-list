package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.dto.FormDTO;
import top.crwenassert.favorites.repository.UserRepository;
import top.crwenassert.favorites.service.UserService;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description:
 * date: 2020/7/21 17:33
 *
 * @author crwen
 * @create 2020-07-21-17:33
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(FormDTO form) {
        User save = null;
        if (form != null) {
            User user = new User();
            user.setEmail(form.getEmail());
            user.setUsername(form.getUsername());
            user.setPassword(form.getPassword());
            user.setTelephone(form.getUsername());
            save = userRepository.save(user);
            log.info("【新增用户】" + user.getUsername());
        }
        return save;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            // TODO
        }
        return user;
    }

    @Override
    public User getUserByPhone(String phone) {
        User user = userRepository.getUserByTelephone(phone);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    @Override
    public List<User> getUserByUsernameOrTelephoneOrEmail(String username, String phone, String email) {
        List<User> users = userRepository.getUserByUsernameOrTelephoneOrEmail(username, phone, email);
        return users;
    }

}
