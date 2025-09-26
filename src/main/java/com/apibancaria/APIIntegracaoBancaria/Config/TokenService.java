package com.apibancaria.APIIntegracaoBancaria.Config;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.Model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${JWT_KEY}")
    private String secret;

    public String generateToken(UserModel userModel) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(userModel.getEmail())
                .withClaim("userId", userModel.getId())
                .withClaim("userName", userModel.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API Integração Bancária")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken (String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT verify = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(verify.getClaim("userId").asLong())
                    .name(verify.getClaim("userName").asString())
                    .email(verify.getSubject())
                    .build());

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
