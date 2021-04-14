package top.crwenassert.favorites.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: Kinds
 * Description:
 * date: 2020/7/29 11:50
 *
 * @author crwen
 * @create 2020-07-29-11:50
 * @since JDK 1.8
 */
@Entity
@Data
@DynamicUpdate
public class Kinds {

    @GeneratedValue
    @Id
    private Long id;

    /*用户id*/
    private Long userId;

    /*种类名称*/
    private String name;

    /*状态 0：删除 1：开启*/
    private Integer status = 1;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
