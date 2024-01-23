package com.example.aasd.repository

import com.example.aasd.model.SigmaUser
import org.springframework.data.jpa.repository.JpaRepository

interface SigmaUserRepository : JpaRepository<SigmaUser, Long> {

    fun findSigmaUserByUsername(username: String): SigmaUser?
}