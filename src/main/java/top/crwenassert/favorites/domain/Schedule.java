package top.crwenassert.favorites.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: Schedule
 * Description:
 * date: 2020/7/21 20:57
 *
 * @author crwen
 * @create 2020-07-21-20:57
 * @since JDK 1.8
 */
//@Entity
@Data
public class Schedule {

    @GeneratedValue
    @Id
    private Long id;

    /*用户id*/
    private Long userId;

    /*状态 0：待完成 1：已完成 2：删除*/
    private Integer status;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    /*提醒时间*/
    private Date alarmTime;
}
