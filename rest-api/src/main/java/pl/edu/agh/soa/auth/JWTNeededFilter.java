package pl.edu.agh.soa.auth;

import io.jsonwebtoken.Jwts;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;
import javax.ws.rs.core.HttpHeaders;

@Provider
@JWTNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTNeededFilter implements ContainerRequestFilter {

    @Inject
    private KeyGenInterface keyGenerator;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        try {
            String authHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authHeader.substring("token".length()).trim();

            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (Exception e) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
