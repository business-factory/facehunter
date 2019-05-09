package com.roihunter.facehunter.dto.slack.event

import com.fasterxml.jackson.annotation.JsonProperty

data class EventDto(
        @JsonProperty("token")
        val token: String?,
        @JsonProperty("challenge")
        val challenge: String?,
        @JsonProperty("type")
        val type: String?
)