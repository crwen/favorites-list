package top.crwenassert.favorites.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ClassName: TaskVO
 * Description:
 * date: 2020/8/31 18:21
 *
 * @author crwen
 * @create 2020-08-31-18:21
 * @since JDK 1.8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskVO {
    @JsonProperty("expectionTime")
    private String expectionTime;

    @JsonProperty("finishTime")
    private String finishTime;

    @JsonProperty("content")
    private String content;

    @JsonProperty("kindId")
    private Long kindId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private Integer status;
}
