package com.example.kotlinAuth.config




import com.example.kotlinAuth.config.jwt.JwtFilter
import com.example.kotlinAuth.models.User
import org.hibernate.boot.model.internal.XMLContext.Default
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.beans.Customizer


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var jwtFilter: JwtFilter

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .authorizeHttpRequests{requests ->
                requests
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
//  TODO Security filters
        return http.build()
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}

