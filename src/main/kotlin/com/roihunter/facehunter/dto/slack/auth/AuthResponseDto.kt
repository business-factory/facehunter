package com.roihunter.facehunter.dto.slack.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponseDto(
        @JsonProperty("ok")
        val ok: Boolean,
        @JsonProperty("access_token")
        val accessToken: String,
        @JsonProperty("team_name")
        val teamName: String,
        @JsonProperty("team_id")
        val teamId: String,
        @JsonProperty("bot")
        val bot: BotAuthResponseDataDto
)