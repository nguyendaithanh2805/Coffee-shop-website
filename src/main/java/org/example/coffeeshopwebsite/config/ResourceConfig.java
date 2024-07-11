package org.example.coffeeshopwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    /*
    - .addResourceHandler("/User/images/**"): se dang ky mot ResourceHandler cho cac tai nguyen co url bat dau bang '/User/images/**'

    - "file:src/main/resources/static/User/images/": chi dinh rang cac tai nguyen tinh duoc yeu cau tu /User/images/
    se duoc tim thay trong thu muc src/main/resources/static/User/images/.

    Cau hinh mac dinh cua Spring boot nam o static, nhung trong truong hop nay luu anh o static/User/image nen phai cau hinh de spring boot hieu
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/User/images/**")
                .addResourceLocations("file:src/main/resources/static/User/images/");
    }
}
