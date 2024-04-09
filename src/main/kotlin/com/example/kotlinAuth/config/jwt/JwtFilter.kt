package com.example.kotlinAuth.config.jwt

import io.micrometer.observation.annotation.Observed
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import org.springframework.util.StringUtils
import java.io.IOException

@Component
class JwtFilter : GenericFilterBean() {
    @Autowired
    private val jwtProvider: JwtProvider? = null

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val token = getTokenFromRequest(servletRequest as HttpServletRequest)

//      getAbstractAuthenticationToken(token) validates the token and obtains access data from it
        val auth = jwtProvider!!.getAbstractAuthenticationToken(token)
        if (auth!=null) SecurityContextHolder.getContext().authentication = auth

        filterChain.doFilter(servletRequest, servletResponse)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader(AUTHORIZATION)
        return if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }


}


