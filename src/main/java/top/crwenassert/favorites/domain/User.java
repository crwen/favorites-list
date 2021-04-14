package top.crwenassert.favorites.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * ClassName: User
 * Description:
 * date: 2020/7/21 17:09
 *
 * @author crwen
 * @create 2020-07-21-17:09
 * @since JDK 1.8
 */
@Entity
@Data
@DynamicUpdate
public class User {

    @GeneratedValue
    @Id
    private Long id;

    private String username;

    private String password;

    private String telephone;

    private String email;

    private String avatar;

    private Date createTime;

    private Date updateTime;
}
