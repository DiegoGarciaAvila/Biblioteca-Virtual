package com.edomex.biblioteca.Configuration;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String pathJsper= "file:///C:/Imagenes/jasper/";
        String pathindex = "file:///C:/Imagenes/pagina/";
        String pathDescarga = "file:///C:/Imagenes/Archivos/";

        //Linux
       // String pathJsper="file:////opt/biblioteca/Imagenes/jasper/";
       // String pathindex = "file:////opt/biblioteca/Imagenes/pagina/";

        registry.addResourceHandler("/jasper/**").addResourceLocations(pathJsper);
        registry.addResourceHandler("/pagina/**").addResourceLocations(pathindex);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
        registry.addResourceHandler("/download/**").addResourceLocations(pathDescarga);

    }

}
