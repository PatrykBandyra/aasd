package com.example.aasd.service

import com.example.aasd.dto.request.SignUpRequest
import com.example.aasd.model.SigmaAuthority
import com.example.aasd.model.SigmaAuthority.SigmaAuthorityValue.USER_DELETE
import com.example.aasd.model.SigmaAuthority.SigmaAuthorityValue.USER_READ
import com.example.aasd.model.SigmaAuthority.SigmaAuthorityValue.USER_WRITE
import com.example.aasd.model.SigmaProfile
import com.example.aasd.model.SigmaUser
import com.example.aasd.model.SigmaUser.SigmaUserType.USER
import com.example.aasd.repository.SigmaUserRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SigmaUserService(
        private val sigmaUserRepository: SigmaUserRepository,
        private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val sigmaUser: SigmaUser = sigmaUserRepository.findSigmaUserByUsername(username)
                ?: throw UsernameNotFoundException("User with username $username not found")
        val authorities: Set<SimpleGrantedAuthority> = sigmaUser.authorities
                .map { authority: SigmaAuthority -> SimpleGrantedAuthority(authority.name.value) }
                .toSet()
        return User.builder()
                .username(sigmaUser.username)
                .password(sigmaUser.password)
                .authorities(authorities)
                .build()
    }

    @Transactional
    fun registerUser(signUpRequest: SignUpRequest): SigmaUser {
        val user = SigmaUser(
                signUpRequest.username,
                signUpRequest.email,
                passwordEncoder.encode(signUpRequest.password),
                USER,
                mutableSetOf(
                        SigmaAuthority(USER_READ),
                        SigmaAuthority(USER_WRITE),
                        SigmaAuthority(USER_DELETE)
                ),
                SigmaProfile(
                        signUpRequest.firstName,
                        signUpRequest.lastName,
                )
        )
        return sigmaUserRepository.save(user)
    }
}