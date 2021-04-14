package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crwenassert.favorites.constant.CookieConstant;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.dto.FormDTO;
import top.crwenassert.favorites.service.MailService;
import top.crwenassert.favorites.service.UserService;
import top.crwenassert.favorites.utils.CookieUtil;
import top.crwenassert.favorites.utils.MD5Util;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.ResultVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: IndexController
 * Description:
 * date: 2020/7/27 0:35
 *
 * @author crwen
 * @create 2020-07-27-0:35
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;
    @Resource
    private MailService mailService;

    @Value("${mail.fromMail.sender}")
    private String sender;

    @PostMapping("/login")
    public ResultVO login(HttpServletResponse response,
                          HttpServletRequest request,
                          User user) {
        if (user == null || user.getUsername().length() == 0 || user.getPassword().length() == 0) {

            return ResultVOUtil.error("用户名或密码不能为空");
        }
        System.out.println(user);

        User find = null;
        if (user.getUsername() != null) {
            find = userService.getUserByUsername(user.getUsername());
        }

        if (find == null) {
            return ResultVOUtil.error("用户名或密码不正确");
        }


        String token = UUID.randomUUID().toString();
        // 设置 token 到 cookie 中
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        // 设置 session
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getUsername());
        session.setMaxInactiveInterval(7200);

        return ResultVOUtil.success();
    }


    @PostMapping("/verify")
    public ResultVO getVerifyCode(User user) {
        List<User> userList = userService.getUserByUsernameOrTelephoneOrEmail(user.getUsername(),user.getTelephone(), user.getEmail());
        if (!userList.isEmpty()) {
            return ResultVOUtil.error("手机或邮箱已被注册");
        }

        String verifyCode = mailService.registerMail(user.getEmail());
        if (verifyCode.length() != 6) {

        }

        Map<String, String> map = new HashMap<>();
        map.put("verifyCode", MD5Util.encrypt(verifyCode));
        map.put("time", (System.currentTimeMillis()) + "");

        return ResultVOUtil.success(map);
    }

    @PostMapping("/register")
    public ResultVO register(FormDTO form) {
        List<User> userList = userService.getUserByUsernameOrTelephoneOrEmail(form.getUsername(),form.getTelephone(), form.getEmail());
        if (!userList.isEmpty()) {
            return ResultVOUtil.error("手机或邮箱已被注册");
        }
        Map<String, String> map = form.getMap();
        if (map.get("verifyCode").equals(MD5Util.encrypt(form.getVerifyCode()))) {
            if (System.currentTimeMillis() > Long.parseLong(map.get("time")) + 15 * 60 * 1000) {
                return ResultVOUtil.error("验证码已超时");
            }
            return ResultVOUtil.error("无效验证码");
        }

        userService.save(form);
        return ResultVOUtil.success();
    }


}
