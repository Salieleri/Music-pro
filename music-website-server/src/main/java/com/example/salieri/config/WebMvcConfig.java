package com.example.salieri.config;

import com.example.salieri.constant.constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(5000);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String OS = System.getProperty("os.name");
        if(OS.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/img/SongPic/**")
                    .addResourceLocations("file:" + constant.RESOURCE_PATH_WIN + "\\img\\SongPic\\");
            registry.addResourceHandler("/Song/**")
                    .addResourceLocations("file:" + constant.RESOURCE_PATH_WIN + "\\Song\\");
            registry.addResourceHandler("/img/Avator/**")
                    .addResourceLocations("file:" + constant.RESOURCE_PATH_WIN + "\\img\\Avator\\");
            registry.addResourceHandler("/img/SongList/**")
                    .addResourceLocations("file:" + constant.RESOURCE_PATH_WIN + "\\img\\SongList\\");
        }
    }
}
