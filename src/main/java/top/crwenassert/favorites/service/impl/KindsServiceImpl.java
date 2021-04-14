package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.domain.Kinds;
import top.crwenassert.favorites.repository.KindsRepository;
import top.crwenassert.favorites.service.KindsService;

import java.util.List;

/**
 * ClassName: KindsServiceImpl
 * Description:
 * date: 2020/8/21 23:32
 *
 * @author crwen
 * @create 2020-08-21-23:32
 * @since JDK 1.8
 */
@Service
@Slf4j
public class KindsServiceImpl implements KindsService {
    @Autowired
    private KindsRepository kindsRepository;

    @Override
    public List<Kinds> getKindsByUser(Long userId, int page, int size) {
        Page<Kinds> kindsPage = kindsRepository.getKindsByUserId(userId, PageRequest.of(page, size));
        return kindsPage.getContent();
    }

    @Override
    public List<Kinds> getKindsByUser(Long userId) {
        List<Kinds> kindsList = kindsRepository.getKindsByUserId(userId);
        return kindsList;
    }

    @Override
    public List<Kinds> getByIds(List<Long> kindsIdList) {
        List<Kinds> kindsList = kindsRepository.getByIdIn(kindsIdList);
        return kindsList;
    }

}
