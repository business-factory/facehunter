package com.roihunter.facehunter.dto.slack.event

import com.fasterxml.jackson.annotation.JsonProperty

data class EventDto(
        @JsonProperty("challenge")
        val challenge: String?,
        @JsonProperty("type")
        val type: String?,
        @JsonProperty("team")
        val team: String?,
        @JsonProperty("event")
        val details: EventDetailsDto?
)