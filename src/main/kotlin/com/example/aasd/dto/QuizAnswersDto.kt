package com.example.aasd.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class QuizAnswersDto(
        val opn: Boolean,
        val con: Boolean,
        val ext: Boolean,
        val agr: Boolean,
        val neu: Boolean,
        @JsonProperty("client_jid")
        val clientJid: String = ""
)