package com.erwindev.apptracker.util

import com.erwindev.apptracker.domain.Student
import groovy.time.TimeCategory
import groovy.time.TimeDuration
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

/**
 * Created by erwinalberto on 6/23/17.
 */
@Component
class TokenUtil {
    @Autowired
    ApplicationSettings applicationSettings

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512

    String generateStudentJwt(Student student){

        Date dateExpiration = new Date()
        def sessionSeconds = new Integer(applicationSettings.jwtExpiresIn)
        use(TimeCategory){
            dateExpiration = dateExpiration + sessionSeconds.seconds
        }

        String jwt = Jwts.builder()
                .setIssuer( applicationSettings.jwtIssuer )
                .setSubject(student.email)
                .setExpiration(dateExpiration)
                .claim('name', student.firstName + ' ' + student.lastName)
                .claim('email', student.email)
                .claim('id', student.id)
                .signWith(SIGNATURE_ALGORITHM, applicationSettings.jwtSecret.getBytes("UTF-8")
        ).compact()

        return jwt
    }

    String decodeStudentJwt(String studentJwt) throws JwtException{
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(applicationSettings.jwtSecret.getBytes("UTF-8"))
                .parseClaimsJws(studentJwt)
        String id = claims.getBody().get("id")
        return id
    }


    String getToken(HttpServletRequest request ) {
        /**
         *  Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName( request, applicationSettings.jwtCookie )
        if ( authCookie != null ) {
            return authCookie.getValue()
        }
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */

        String authHeader = request.getHeader(applicationSettings.jwtHeader)
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7)
        }

        return null
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request
     *            The HTTP request object.
     * @param name
     *            The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i]
            }
        }
        return null
    }    
}
