package com.example.kotlinAuth.config

import com.example.kotlinAuth.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails : UserDetails {
    private var login: String? = null
    private var password: String? = null
    private var grantedAuthorities: Collection<GrantedAuthority?>? = null
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return grantedAuthorities!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return login!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun fromUserEntityToCustomUserDetails(user: User): CustomUserDetails {
            val c = CustomUserDetails()
            c.login = user.email
            c.password = user.password
            c.grantedAuthorities = listOf(SimpleGrantedAuthority(user.roleid!!.name))
            return c
        }
    }
}