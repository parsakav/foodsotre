package org.example.sotre.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // تمام مسیرهای API را مجاز می‌کند
                .allowedOriginPatterns("*")  // از هر دامنه‌ای اجازه دسترسی می‌دهد
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // تمام روش‌های HTTP مجاز است
                .allowedHeaders("*")  // تمام هدرها مجاز است
                .allowCredentials(true);  // اگر به کوکی‌ها یا توکن‌ها نیاز دارید
    }
}
