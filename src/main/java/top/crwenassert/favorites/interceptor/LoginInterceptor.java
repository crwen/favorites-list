package top.crwenassert.favorites.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.crwenassert.favorites.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName: LoginInterceptor
 * Description:
 * date: 2020/7/27 15:29
 *
 * @author crwen
 * @create 2020-07-27-15:29
 * @since JDK 1.8
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieUtil.get(request, "token");
        if (cookie == null) {
            log.warn("【登陆校验】 Cookie中查不到token");
            //throw new SellerAuthorizeException();
            return false;
        }
        // 查 session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        if (null == username || username.length() == 0) {
            log.warn("【登录校验】 Session 中查不到用户");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
