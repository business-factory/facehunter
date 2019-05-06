package com.roihunter.facehunter.dto.slack.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserProfileDto(
        @JsonProperty("title")
        val title: String,
        @JsonProperty("real_name")
        val realName: String?,
        @JsonProperty("image_original")
        val imageOriginal: String?,
        @JsonProperty("image_512")
        val image512: String?,
        @JsonProperty("is_custom_image")
        val isCustomImage: Boolean
)