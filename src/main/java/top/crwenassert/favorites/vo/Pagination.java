package top.crwenassert.favorites.vo;

import lombok.Data;

/**
 * ClassName: Pagination
 * Description:
 * date: 2020/9/1 23:57
 *
 * @author crwen
 * @create 2020-09-01-23:57
 * @since JDK 1.8
 */
@Data
public class Pagination {
    private Integer page;

    private Integer size;

    private Long total;

    public Pagination(Integer page, Long total) {
        this.page = page;
        this.total = total;
    }

    public Pagination(Integer page, Integer size, Long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }
}
