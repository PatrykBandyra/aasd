package com.example.aasd.agent

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
internal class AgentsConfig(
        @Value("\${sigma.agents-url}") private val agentsUrl: String
) {

    @Bean
    internal fun restClient(): RestClient {
        return RestClient.builder()
                .baseUrl(agentsUrl)
                .build()
    }
}