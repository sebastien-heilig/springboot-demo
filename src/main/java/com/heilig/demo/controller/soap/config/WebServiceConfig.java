package com.heilig.demo.controller.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  public static final String NAMESPACE = "http://com.heilig.demo/wsdl/demo-user.wsdl";

  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {

    var servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    return new ServletRegistrationBean(servlet, "/soap/*");
  }

  @Bean(name = "demo")
  public Wsdl11Definition defaultWsdl11Definition() {
    var definition = new SimpleWsdl11Definition();
    definition.setWsdl(new ClassPathResource("wsdl/demo-user.wsdl"));
    return definition;
  }

}
