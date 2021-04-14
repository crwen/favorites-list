package top.crwenassert.favorites.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ClassName: ResultVO
 * Description:
 * date: 2020/7/21 17:49
 *
 * @author crwen
 * @create 2020-07-21-17:49
 * @since JDK 1.8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    /**
     *  状态码
     */
    private Integer code;

    /**
     *  提示信息
     */
    private String msg;

    /**
     *  具体内容
     */
    private T data;

    @JsonProperty("page")
    private Pagination pagination;

}
