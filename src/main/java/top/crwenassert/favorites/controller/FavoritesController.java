package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.crwenassert.favorites.domain.Favorites;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.utils.ConvertUtil;
import top.crwenassert.favorites.vo.FavoritesVO;
import top.crwenassert.favorites.service.FavoritesService;
import top.crwenassert.favorites.service.UserService;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FavoritesController
 * Description:
 * date: 2020/7/28 8:01
 *
 * @author crwen
 * @create 2020-07-28-8:01
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/favorites")
@Slf4j
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO<Favorites> list(HttpServletRequest request) {
        long userId = Long.parseLong(request.getHeader("user_id"));
        User user = userService.getUserById(userId);
        //User user = userService.getUserByUsername(username);
        if (user == null) {
            log.warn("【收藏收藏列表】用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        List<Favorites> favoritesList = new ArrayList<>();
        List<FavoritesVO> favoritesVOList = new ArrayList<>();
        if (user != null) {
            favoritesList = favoritesService.getFavoritesByUser(user.getId());
            favoritesVOList = ConvertUtil.convertFavorites2FavoritesVO(favoritesList);
        }

        return ResultVOUtil.success(favoritesVOList);
    }


    @PostMapping("/add")
    public ResultVO add(HttpServletRequest request, Favorites favorites) {
        long userId = Long.parseLong(request.getHeader("user_id"));
        User user = userService.getUserById(userId);
        if (user == null) {
            log.warn("【收藏收藏列表】用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        favorites.setUserId(user.getId());
        Favorites find = favoritesService.getByUserIdAndName(user.getId(), favorites.getName());
        if (find != null) {
            log.warn("【添加收藏夹】 用户收藏夹已存在", favorites.getName());
            return ResultVOUtil.error("收藏夹已存在");
        }

        favoritesService.save(favorites);

        return ResultVOUtil.success();
    }

    @PostMapping("/delete")
    public ResultVO delete(HttpServletRequest request, FavoritesVO favoritesVO) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        // 获取用户账户下的所有收藏夹
        List<Favorites> favoritesList = favoritesService.getFavoritesByUser(user.getId());
        List<String> nameList = favoritesVO.getNameList();
        List<Favorites> deleteList = new ArrayList<>();

        for (Favorites favorites : favoritesList) {
            if (nameList.contains(favorites.getName())) {
                deleteList.add(favorites);
            }
        }
        if (!deleteList.isEmpty() && deleteList.size() != 0) {
            favoritesService.deleteInBatch(deleteList);
        } else {
            log.warn("【删除收藏夹】收藏夹不存在", favoritesVO.getNameList());
            return ResultVOUtil.error("收藏夹不存在");
        }

        //Favorites favorites = favoritesService.getByUserIdAndName(user.getId(), name);
        //if (favorites == null) {
        //    return ResultVOUtil.error("收藏夹不存在");
        //}
        //favoritesService.delete(favorites.getId());

        return ResultVOUtil.success();
    }

}
