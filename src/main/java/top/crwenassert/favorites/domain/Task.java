package top.crwenassert.favorites.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: Schedule
 * Description:
 * date: 2020/7/21 20:45
 *
 * @author crwen
 * @create 2020-07-21-20:45
 * @since JDK 1.8
 */
@Entity
@Data
public class Task {

    @GeneratedValue
    @Id
    private Long id;

    /*所属代办类表*/
    //private Long scheduleId;
    private Long kindId;

    /*代办内容*/
    private String content;

    /*状态 0：待完成 1：已完成 2：删除*/
    private Integer status = 0;

    /*预期完成时间*/
    private Date expectionTime;

    /*实际完成时间*/
    private Date finishTime;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
