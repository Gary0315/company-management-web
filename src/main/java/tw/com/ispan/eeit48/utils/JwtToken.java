package tw.com.ispan.eeit48.utils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtToken implements Serializable{
	 private static final long EXPIRATION_TIME = 10 * 60 * 1000;
	    /**
	     * JWT SECRET KEY
	     */
	    private static final String SECRET = "chunhao is big god";

	    /**
	     * 簽發JWT
	     */
	    public String generateToken(HashMap<String, ?> userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	     
	        claims.put( "empid", userDetails.get("empid") );
	        claims.put( "deptid", userDetails.get("deptid") );
	        claims.put( "positionid", userDetails.get("positionid") );

	        return Jwts.builder()
	                .setClaims( claims )
	                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ) )
	                .signWith( SignatureAlgorithm.HS512, SECRET )
	                .compact();
	    }

	    /**
	     * 驗證JWT
	     */
	    public void validateToken(String token) throws AuthException {
	        try {
	            Jwts.parser()
	                    .setSigningKey( SECRET )
	                    .parseClaimsJws( token );
	        } catch (SignatureException e) {
	            throw new AuthException("Invalid JWT signature.");
	        }
	        catch (MalformedJwtException e) {
	            throw new AuthException("Invalid JWT token.");
	        }
	        catch (ExpiredJwtException e) {
	            throw new AuthException("Expired JWT token");
	        }
	        catch (UnsupportedJwtException e) {
	            throw new AuthException("Unsupported JWT token");
	        }
	        catch (IllegalArgumentException e) {
	            throw new AuthException("JWT token compact of handler are invalid");
	        }
	    }
}
