package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.crwenassert.favorites.domain.Kinds;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.service.KindsService;
import top.crwenassert.favorites.service.UserService;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: KindsController
 * Description:
 * date: 2020/8/21 23:39
 *
 * @author crwen
 * @create 2020-08-21-23:39
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/kinds")
@Slf4j
public class KindsController {

    @Autowired
    private KindsService kindsService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResultVO<Kinds> getKinds(HttpServletRequest request,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "5") Integer size) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        if (user == null) {
            log.warn("【查找代办种类】用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        List<Kinds> kindsList = kindsService.getKindsByUser(user.getId(), 0, 5);

        return ResultVOUtil.success(kindsList);
    }


}
