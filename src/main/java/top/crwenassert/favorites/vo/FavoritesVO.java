package top.crwenassert.favorites.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName: FavoritesVO
 * Description:
 * date: 2020/7/28 11:32
 *
 * @author crwen
 * @create 2020-07-28-11:32
 * @since JDK 1.8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritesVO {

    @JsonProperty("id")
    private Long id;

    /*父收藏夹 id*/
    @JsonProperty("parentId")
    private Long parentId;

    /*用户id*/
    //@JsonProperty("userId")
    //private Long userId;

    /*收藏夹名称*/
    @JsonProperty("name")
    private String name;

    @JsonProperty("nameList")
    private List<String> nameList;

    /*状态 0：删除 1：开启*/
    @JsonProperty("status")
    private Integer status = 1;


    /*更新时间*/
    @JsonProperty("updateTime")
    private Date updateTime;
}
