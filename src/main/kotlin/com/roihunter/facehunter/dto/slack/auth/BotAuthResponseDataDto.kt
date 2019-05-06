package com.roihunter.facehunter.dto.slack.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class BotAuthResponseDataDto(
        @JsonProperty("bot_user_id")
        val botUserId: String,
        @JsonProperty("bot_access_token")
        val botToken: String
)