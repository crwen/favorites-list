package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import top.crwenassert.favorites.domain.Collection;
import top.crwenassert.favorites.domain.Favorites;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.service.CollectionService;
import top.crwenassert.favorites.service.FavoritesService;
import top.crwenassert.favorites.service.UserService;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.CollectionVO;
import top.crwenassert.favorites.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: CollectionController
 * Description:
 * date: 2020/7/28 13:23
 *
 * @author crwen
 * @create 2020-07-28-13:23
 * @since JDK 1.8
 */
@RestController
@Slf4j
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private UserService userService;
    @Autowired
    private FavoritesService favoritesService;

    /**
     *  列出对应收藏夹下的收藏
     * @param id 收藏夹 id
     * @return
     */
    @GetMapping("/list/{id}")
    public ResultVO<Collection> list(@PathVariable("id") Long id,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "5") Integer size) {
        List<CollectionVO> collectionVOList = collectionService.getByFavoriteId(id, PageRequest.of(page - 1, size));

        return ResultVOUtil.success(collectionVOList);
    }

    /**
     *  添加收藏夹
     * @param collection
     * @return
     */
    @PostMapping("/add")
    public ResultVO save(Collection collection) {
        if (collection != null) {
            collectionService.save(collection);
        }
        return ResultVOUtil.success();
    }

    /**
     * 删除收藏夹
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO delete(HttpServletRequest request, Long id) {

        Collection collection = collectionService.getById(id);

        if (collection == null) {
            log.warn("【删除收藏】 该收藏不存在 ", id);
            return ResultVOUtil.error("该收藏不存在");
        }
        Favorites favorites = favoritesService.getFavoritesById(collection.getFavoriteId());
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        if (favorites == null || favorites.getUserId() != user.getId()) {
            log.warn("【删除收藏】 该收藏不存在或该收藏不属于该用户", favorites);
            return ResultVOUtil.error("非法删除操作");
        }

        collectionService.delete(id);

        return ResultVOUtil.success();
    }

    /**
     * 更新收藏夹
     * @param collection
     * @return
     */
    @PostMapping("/update")
    public ResultVO update(Collection collection) {

        Collection find = collectionService.getById(collection.getId());
        Favorites favorites = favoritesService.getFavoritesById(collection.getFavoriteId());
        if (find == null || favorites == null) {
            log.warn("【更新收藏夹】 收藏信息不正确");
            return ResultVOUtil.error("收藏信息不正确");
        }

        //collection.setCreateTime(find.getCreateTime());
        //collection.setId(find.getId());

        //BeanUtils.copyProperties(find, collection);
        collection.setCreateTime(find.getCreateTime());
        collectionService.save(collection);

        return ResultVOUtil.success();
    }

}
