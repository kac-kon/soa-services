package pl.edu.agh.soa.rest_connector;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;

public class Client {
    private static final String url = "http://localhost:8080/rest-api";
    private final ResteasyClient resteasyClient;
    private String username = "";
    private String password = "";
    private String token = null;

    public Client() {
        this.resteasyClient = new ResteasyClientBuilder().build();
    }

    public Client(String username, String password) {
        this.resteasyClient = new ResteasyClientBuilder().build();
        this.username = username;
        this.password = password;
        authorize();
    }

    public void authorize() {
        ResteasyWebTarget target = resteasyClient.target(url + "/auth/login");
        Form form = new Form();
        form.param("login", this.username);
        form.param("password", this.password);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        MultivaluedMap<String, String> headers = response.getStringHeaders();
        this.token = headers.getFirst("authorization");
    }

    public String getToken() {
        return this.token;
    }

    public void endSession() {
        if (!resteasyClient.isClosed())
            resteasyClient.close();
    }

    public ResteasyClient getClient(){
        return this.resteasyClient;
    }
}
