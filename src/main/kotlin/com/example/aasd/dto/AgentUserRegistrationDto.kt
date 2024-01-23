package com.example.aasd.dto

import com.example.aasd.agent.AgentType

data class AgentUserRegistrationDto(
        val username: String,
        val agentType: AgentType
)