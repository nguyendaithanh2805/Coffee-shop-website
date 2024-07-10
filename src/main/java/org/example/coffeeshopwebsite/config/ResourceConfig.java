package org.example.coffeeshopwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    /*
    - .addResourceHandler("/user/images/**"): se dang ky mot ResourceHandler cho cac tai nguyen co url bat dau bang '/user/images/**'

    - "file:src/main/resources/static/user/images/": chi dinh rang cac tai nguyen tinh duoc yeu cau tu /user/images/
    se duoc tim thay trong thu muc src/main/resources/static/user/images/.

    Cau hinh mac dinh cua Spring boot nam o static, nhung trong truong hop nay luu anh o static/user/image nen phai cau hinh de spring boot hieu
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/images/**")
                .addResourceLocations("file:src/main/resources/static/user/images/");
    }
}
