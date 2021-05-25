package pl.edu.agh.soa.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Api(value = "Auth")
@Path("/auth")
public class AuthService {

    @Inject
    private KeyGenInterface keyGenerator;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response authUser(
            @FormParam("login") String login,
            @FormParam("password") String password
    ) {
        try {
            String token = null;
            boolean isValid = authenticate(login, password);
            if (isValid)
                token = issueToken(login);
            if (token != null) {
                return Response
                        .ok()
                        .header(HttpHeaders.AUTHORIZATION, "token " + token)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        }
    }

    private boolean authenticate(String login, String password) throws SecurityException {
        if ("123".equals(login) && "123".equals(password)) {
            return true;
        } else {
            throw new SecurityException("Invalid user or password");
        }
    }

    private String issueToken(String login) {
        keyGenerator = new KeyGen();
        Key key = keyGenerator.generateKey();
        return Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(10L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
