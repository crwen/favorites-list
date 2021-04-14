package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crwenassert.favorites.service.UserService;

/**
 * ClassName: UserController
 * Description:
 * date: 2020/7/21 17:05
 *
 * @author crwen
 * @create 2020-07-21-17:05
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${mail.fromMail.sender}")
    private String sender;







}
