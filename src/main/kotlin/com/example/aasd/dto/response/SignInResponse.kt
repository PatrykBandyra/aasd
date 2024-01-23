package com.example.aasd.dto.response

data class SignInResponse(
        val token: String,
        val expiresAt: Long,
)
