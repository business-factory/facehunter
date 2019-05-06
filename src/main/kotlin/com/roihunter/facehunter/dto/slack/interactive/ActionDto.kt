package com.roihunter.facehunter.dto.slack.interactive

import com.fasterxml.jackson.annotation.JsonProperty

data class ActionDto(
        @JsonProperty("block_id")
        val blockId: String?,
        @JsonProperty("action_id")
        val actionId: String,
        @JsonProperty("value")
        val value: String?
)