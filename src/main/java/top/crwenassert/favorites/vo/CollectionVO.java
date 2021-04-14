package top.crwenassert.favorites.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: CollectionVO
 * Description:
 * date: 2020/8/13 15:59
 *
 * @author crwen
 * @create 2020-08-13-15:59
 * @since JDK 1.8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollectionVO {
    @JsonProperty("id")
    private Long id;

    /*所属收藏夹id*/
    @JsonProperty("favoriteId")
    private Long favoriteId;

    /*收藏名*/
    @JsonProperty("title")
    private String title;

    /*收藏链接*/
    @JsonProperty("url")
    private String url;

    /*状态 0：已删除； 1：开启*/
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("updateTime")
    private Date updateTime;
}
