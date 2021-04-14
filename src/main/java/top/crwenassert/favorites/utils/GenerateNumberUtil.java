package top.crwenassert.favorites.utils;

import java.util.Random;

/**
 * ClassName: VerifyCodeUtil
 * Description:
 * date: 2020/7/27 12:48
 *
 * @author crwen
 * @create 2020-07-27-12:48
 * @since JDK 1.8
 */
public class GenerateNumberUtil {

    public synchronized static String generateVerifyCode() {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int num = random.nextInt(10);
            code.append(num);
        }
        return code.toString().trim();
    }

    //public synchronized static Long generateId() {
    //    Long id = System.currentTimeMillis() + ;
    //}
}
