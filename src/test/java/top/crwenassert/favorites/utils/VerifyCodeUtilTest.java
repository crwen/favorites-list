package top.crwenassert.favorites.utils;

import org.junit.jupiter.api.Test;

/**
 * ClassName: VerifyCodeUtilTest
 * Description:
 * date: 2020/7/27 13:58
 *
 * @author crwen
 * @create 2020-07-27-13:58
 * @since JDK 1.8
 */
class VerifyCodeUtilTest {

    @Test
    void generateVerifyCode() {
        System.out.println(GenerateNumberUtil.generateVerifyCode());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start = start + 1 * 1000;
        System.out.println(start);
        System.out.println(System.currentTimeMillis());
    }
}