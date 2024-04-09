package com.example.kotlinAuth.config.jwt

import com.example.kotlinAuth.config.CustomAbstractAuthenticationToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.micrometer.observation.annotation.Observed
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.stream.Collectors


@Observed(name = "JwtProvider")
@Component
class JwtProvider {
//    TODO ненадежно
    var jwtSecret = "7Tnx6|iVJ8?fk{9"

    var jwtKeycloakDecoder: JwtDecoder = JwtDecoders.fromIssuerLocation("http://keycloak:8080/realms/kotlinAuth")
// host.docker.internal
    fun generateToken(email: String?, roles: List<String>): String {
        val date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }


//
    fun getEmailFromToken(token: String?): String? {
        try {
            val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
            return claims.subject
        } catch (e: Exception) { }
        try {

        } catch (e: Exception) {
            return  jwtKeycloakDecoder.decode(token).claims["email"] as String
        }
        return null

    }

    fun getAbstractAuthenticationToken(token: String?): AbstractAuthenticationToken? {
        val copyTolen = token.toString()
        try {
//          Verification for server token
            val roles = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body["roles"] as List<String>
            val abstractAuthenticationToken = CustomAbstractAuthenticationToken(
                roles.stream()
                    .map { SimpleGrantedAuthority(it) }
                    .collect(Collectors.toList())
            )
            return abstractAuthenticationToken
        } catch (e: Exception) { }

        try {


//          Verification for Keycloak token
            val roles = jwtKeycloakDecoder.decode(copyTolen).claims["roles"] as List<String>
            val abstractAuthenticationToken = CustomAbstractAuthenticationToken(
                roles
                    .filter { it.startsWith("ROLE_") }
                    .stream()
                    .map { SimpleGrantedAuthority(it)}
                    .collect(Collectors.toList())

            )
            return abstractAuthenticationToken

        } catch (e: Exception) {

//            val url = URL("http://keycloak:8080/realms/kotlinAuth")
//            val connection = url.openConnection() as HttpURLConnection
//            connection.requestMethod = "GET"
//            println("Response Code: ${connection.responseCode}")
//            connection.inputStream.bufferedReader().use {
//                it.lines().forEach { line ->
//                    println(line)
//                }
//            }
//
//
//            println(jwtKeycloakDecoder.toString())
//            println(e.message)
        }
        return null

    }
}