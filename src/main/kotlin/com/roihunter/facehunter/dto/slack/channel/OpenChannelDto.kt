package com.roihunter.facehunter.dto.slack.channel

import com.fasterxml.jackson.annotation.JsonProperty

data class OpenChannelDto(
        @JsonProperty("ok")
        val ok: Boolean,
        @JsonProperty("channel")
        val channel: ChannelDto
)