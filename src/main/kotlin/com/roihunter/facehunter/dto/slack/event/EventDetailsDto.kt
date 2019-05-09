package com.roihunter.facehunter.dto.slack.event

import com.fasterxml.jackson.annotation.JsonProperty

data class EventDetailsDto(
        @JsonProperty("text")
        val text: String?,
        @JsonProperty("user")
        val user: String?
)