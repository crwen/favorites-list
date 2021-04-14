package top.crwenassert.favorites.utils;

import java.security.MessageDigest;

/**
 * ClassName: MD5Util
 * Description:
 * date: 2020/7/21 17:44
 *
 * @author crwen
 * @create 2020-07-21-17:44
 * @since JDK 1.8
 */
public class MD5Util {

    public static String encrypt(String dataStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(dataStr.getBytes("UTF8"));
            byte s[] = md5.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
                        .substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}
