package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.domain.Collection;
import top.crwenassert.favorites.eumn.CollectionEnum;
import top.crwenassert.favorites.repository.CollectionRepository;
import top.crwenassert.favorites.service.CollectionService;
import top.crwenassert.favorites.utils.ConvertUtil;
import top.crwenassert.favorites.vo.CollectionVO;

import java.util.List;

/**
 * ClassName: CollectionServiceImpl
 * Description:
 * date: 2020/7/28 13:20
 *
 * @author crwen
 * @create 2020-07-28-13:20
 * @since JDK 1.8
 */
@Service
@Slf4j
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public Collection getById(Long id) {
        Collection collection = collectionRepository.getById(id);

        return collection;
    }

    @Override
    public List<CollectionVO> getByFavoriteId(Long favoriteId, Pageable pageable) {
        //Page<Collection> collectionPage = collectionRepository.getAllByFavoriteId(favoriteId, pageable);
        Page<Collection> collectionPage = collectionRepository.getAllByFavoriteIdAndStatus(favoriteId, 1, pageable);
        List<CollectionVO> collectionVOList = ConvertUtil.convertCollection2CollectionVO(collectionPage.getContent());
        Page<CollectionVO> collectionVOPage = new PageImpl<>(collectionVOList, pageable, collectionPage.getTotalElements());
        return collectionVOPage.getContent();
    }

    @Override
    public Collection save(Collection collection) {
        Collection save = null;
        if (collection != null) {
            save = collectionRepository.save(collection);
            log.info("【新增收藏】title: %s, url: %s", collection.getTitle(), collection.getUrl());
        }
        return save;
    }

    @Override
    public void delete(Long id) {
        Collection collection = collectionRepository.getById(id);
        if (collection.getStatus() == 0) {
            log.error("【删除收藏】 没有该收藏");
            return;
        }
        // 更新状态
        collection.setStatus(CollectionEnum.DELETED.getStatus());
        // 更新
        collectionRepository.save(collection);
        //collectionRepository.deleteById(id);
    }
}
