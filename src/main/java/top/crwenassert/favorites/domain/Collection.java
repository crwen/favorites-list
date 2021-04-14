package top.crwenassert.favorites.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: Collection
 * Description:
 * date: 2020/7/21 20:32
 *
 * @author crwen
 * @create 2020-07-21-20:32
 * @since JDK 1.8
 */
@Entity
@Data
@DynamicUpdate
public class Collection {

    @GeneratedValue
    @Id
    private Long id;

    /*所属收藏夹id*/
    private Long favoriteId;

    /*收藏名*/
    private String title;

    /*收藏链接*/
    private String url;

    /*状态 0：已删除； 1：开启*/
    private Integer status = 1;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
