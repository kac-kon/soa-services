package pl.edu.agh.soa.lab;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.logging.Logger;

@Stateless
@WebService
public class HelloWorld {

    public static final Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());

    @WebMethod
    public String hello(@WebParam(name = "name") String name) {
        LOGGER.info("hello invoked with: " + name);
        return "Hello " + name;
    }

}
