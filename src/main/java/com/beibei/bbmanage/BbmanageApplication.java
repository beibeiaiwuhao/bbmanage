package com.beibei.bbmanage;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringBootServletInitializer 的作用就是在tomcat启动的时候，执行内置的config
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.beibei.bbmanage.repository")
public class BbmanageApplication extends SpringBootServletInitializer implements WebApplicationInitializer {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid () {
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    //注册后台界面servlet ,用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建StatViewServlet 绑定到/durid/路径下
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet() , "/druid/*" );
        //开启后，访问localhost/druid就可以看到druid管理后台
        Map<String,String> param = new HashMap<>();
        param.put("loginUsername","admin");
        param.put("loginPassword","123456");
        param.put("allow","");//那些ip允许访问后台，""代表所有地址
        param.put("deny","33.33.3.3");//不允许这个IP访问
        bean.setInitParameters(param);
        return bean;
    }

    //用于监听获取应用的数据，Filter 用于收集数据，Servlet用于展现数据
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());//设置过滤器
        bean.addUrlPatterns("/*");

        Map<String,String> param = new HashMap<>();
        //排除静态资源
        param.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(param);
        return bean;
    }



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        System.out.println("War包启动");
        return application.sources(BbmanageApplication.class);
    }



    public static void main(String[] args) {
        SpringApplication.run(BbmanageApplication.class, args);
    }
}
