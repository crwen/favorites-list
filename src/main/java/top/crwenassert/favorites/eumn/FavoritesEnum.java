package top.crwenassert.favorites.eumn;

/**
 * ClassName: CollectionEnum
 * Description:
 * date: 2020/8/31 22:26
 *
 * @author crwen
 * @create 2020-08-31-22:26
 * @since JDK 1.8
 */
public enum FavoritesEnum {

    DELETED(0, "已删除"),
    NORMAL(1, "未超过预取时间");

    String msg;
    Integer status;
    FavoritesEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }
}
