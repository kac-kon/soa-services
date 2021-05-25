package pl.edu.agh.soa.rest;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application {

    public App(){
        BeanConfig beanConfig = new BeanConfig();

        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("Student data application");
        beanConfig.setBasePath("rest-api/api");
        beanConfig.setResourcePackage("pl.edu.agh.soa.rest");
        beanConfig.setScan(true);
    }
}
