package top.crwenassert.favorites.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * ClassName: FormDTO
 * Description:
 * date: 2020/7/27 14:20
 *
 * @author crwen
 * @create 2020-07-27-14:20
 * @since JDK 1.8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("verifyCode")
    private String verifyCode;

    @JsonProperty("map")
    private Map<String, String> map;

}
