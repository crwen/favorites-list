package top.crwenassert.favorites.service;

import top.crwenassert.favorites.domain.Kinds;

import java.util.List;

/**
 * ClassName: KindsService
 * Description:
 * date: 2020/8/21 23:30
 *
 * @author crwen
 * @create 2020-08-21-23:30
 * @since JDK 1.8
 */
public interface KindsService {
    List<Kinds> getKindsByUser(Long userId, int page, int size);
    List<Kinds> getKindsByUser(Long userId);

    List<Kinds> getByIds(List<Long > kindsIdList);
}
