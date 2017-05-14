package br.edu.utfpr.posjava.soa.security;

import br.edu.utfpr.posjava.soa.model.security.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by talyssondecastro on 13/05/17.
 */
@Component
public class TokenUtils {

    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";

    private String secret = "posjava";
    private Long expiration = 604800L; // 1 Semana
    //private Long expiration = 30L; // 20 segundos

    public String getUsernameFromToken(String token) {
        String username;

        final Claims claims = this.getClaimsFromToken(token);

        username = claims.getSubject();

        return username;

    }

    private Claims getClaimsFromToken(String token) {

        Claims claims = Jwts.
                parser().
                setSigningKey(this.secret).
                parseClaimsJws(token).
                getBody();

        return claims;

    }

    public Date getCreatedDateFromToken(String token) {

        Date created = new Date( (Long) this.getClaimsFromToken(token).get( "created" ));

        return created;

    }

    public Date getExpirationDateFromToken(String token) {

        Date expiration =this.getClaimsFromToken(token).getExpiration();

        return expiration;
    }

    public String getAudienceFromToken(String token) {

        String audience = (String) this.getClaimsFromToken(token).get("audience");

        return audience;

    }

    private Date generateCurrentDate() {

        return new Date(System.currentTimeMillis());

    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration + 1000);
    }

    private Boolean isTokenExpired(String token) {

        Date expiration = this.getExpirationDateFromToken(token);

        return expiration.before( this.generateCurrentDate() );

    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {

        return lastPasswordReset != null && created.before(lastPasswordReset);

    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);

        return this.AUDIENCE_TABLET.equals(audience) ||
                this.AUDIENCE_MOBILE.equals(audience);

    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap() {
            {
                put( "sub", userDetails.getUsername() );
                put( "audience", AUDIENCE_WEB );
                put( "created", generateCurrentDate() );
            }
        };

        return generateToken(claims);


    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().
                setClaims(claims).
                setExpiration( this.generateExpirationDate() ).
                signWith( SignatureAlgorithm.HS512, this.secret ).
                compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {

        Date created = this.getCreatedDateFromToken(token);

        return (
                !(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) &&
                        (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token) )

        );

    }

    public String refreshToken(String token) {

        Claims claims = this.getClaimsFromToken( token );
        claims.put( "created", this.generateCurrentDate() );

        String refreshToken = this.generateToken(claims);

        return refreshToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        Usuario user = (Usuario) userDetails;

        final String username = getUsernameFromToken(token);

        final Date created = getCreatedDateFromToken(token);

        return (username.equals(user.getUsername())
                && !(isTokenExpired(token))
                && !(isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset()))
        );

    }


}
