package top.crwenassert.favorites.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * ClassName: InterceptorConfig
 * Description:
 * date: 2020/7/27 15:32
 *
 * @author crwen
 * @create 2020-07-27-15:32
 * @since JDK 1.8
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LoginInterceptor())
        //        .addPathPatterns("/**")
        //        .excludePathPatterns("/login")
        //        .excludePathPatterns("/register")
        //        .excludePathPatterns("/verify");
        //super.addInterceptors(registry);
    }
}
