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
public enum TaskEnum {

    DELETED(-1, "已删除"),
    NO_ALARM(0, "不需要提醒"),
    NORMAL(1, "未超过预取时间"),
    ALARM(2, "超时未完成"),
    FINISHED(3, "已完成");

    String msg;
    Integer status;
    TaskEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }
}
