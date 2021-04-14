package top.crwenassert.favorites.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: Favorites
 * Description:
 * date: 2020/7/21 20:37
 *
 * @author crwen
 * @create 2020-07-21-20:37
 * @since JDK 1.8
 */
@Entity
@Data
@DynamicUpdate
public class Favorites {

    @GeneratedValue
    @Id
    private Long id;

    /*父收藏夹 id*/
    private Long parentId = 0L;

    /*用户id*/
    private Long userId;

    /*收藏夹名称*/
    private String name;

    /*状态 0：删除 1：开启*/
    private Integer status = 1;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
