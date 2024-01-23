package com.example.aasd.agent

import com.example.api.dto.AgentUserRegistrationDto
import com.example.api.dto.QuizAnswersDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class AgentsService(
        private val restClient: RestClient
) {

    fun registerUser(agentUserRegistration: AgentUserRegistrationDto): HttpStatusCode {
        return restClient.post()
                .uri("/register_user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(agentUserRegistration)
                .retrieve()
                .toBodilessEntity()
                .statusCode
    }

    fun getQuizQuestions(): List<String>? {
        return restClient.get()
                .uri("/get_personality_test")
                .retrieve()
                .body(typeRef<List<String>>())
    }

    fun answerQuizQuestions(quizAnswers: QuizAnswersDto): HttpStatusCode {
        return restClient.post()
                .uri("/send_personality_results")
                .contentType(MediaType.APPLICATION_JSON)
                .body(quizAnswers)
                .retrieve()
                .toBodilessEntity()
                .statusCode
    }

    private inline fun <reified T> typeRef() = object : ParameterizedTypeReference<T>() {}
}