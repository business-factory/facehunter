package com.roihunter.facehunter.dto.slack.block

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Images also have text for title, but we don't use that in Face Hunter.
 */
data class ImageBlockDto(
        @JsonProperty("type")
        val type: String = "image",
        @JsonProperty("alt_text")
        val altText: String,
        @JsonProperty("image_url")
        val imageUrl: String
) : StructuredBlock