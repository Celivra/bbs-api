package com.celivra.socialmedia;

import com.celivra.socialmedia.Entity.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dir = fileProperties.getUploadDir();
        if (!dir.endsWith("/")) dir += "/";

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + dir);
    }
}
