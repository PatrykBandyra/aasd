package com.example.aasd.agent

import com.example.api.dto.AgentUserRegistrationDto
import com.example.api.dto.QuizAnswersDto
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.PostExchange

interface AgentsClient {

    @PostExchange("/register_user")
    fun registerUser(agentUserRegistration: AgentUserRegistrationDto)

    @GetExchange("/get_personality_test")
    fun getQuizQuestions(): List<String>?
    
    @PostExchange("/send_personality_results")
    fun answerQuizQuestions(quizAnswers: QuizAnswersDto)
}