package com.roihunter.facehunter.dto.slack.interactive

import com.fasterxml.jackson.annotation.JsonProperty
import com.roihunter.facehunter.dto.slack.channel.ChannelDto
import com.roihunter.facehunter.dto.slack.user.UserDto

data class PayloadDto(
        @JsonProperty("type")
        val type: String,
        @JsonProperty("response_url")
        val responseUrl: String,
        @JsonProperty("user")
        val user: UserDto,
        @JsonProperty("channel")
        val channel: ChannelDto,
        @JsonProperty("actions")
        val actions: List<ActionDto>,
        @JsonProperty("team")
        val team: TeamDto
)