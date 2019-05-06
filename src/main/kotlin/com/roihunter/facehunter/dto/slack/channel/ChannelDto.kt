package com.roihunter.facehunter.dto.slack.channel

import com.fasterxml.jackson.annotation.JsonProperty

data class ChannelDto(
        @JsonProperty("id")
        val id: String
)