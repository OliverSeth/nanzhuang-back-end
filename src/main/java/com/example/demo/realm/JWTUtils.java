package com.example.demo.realm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/3 15:24
 */
public class JWTUtils {
    private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    public static String sign(String userName, String password) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create()
                .withClaim("username", userName)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token, String username, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims=jwt.getClaims();
        return claims.get("username").asString().equals(username);
    }

    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }
}
