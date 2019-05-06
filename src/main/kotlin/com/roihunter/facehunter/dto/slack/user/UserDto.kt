package com.roihunter.facehunter.dto.slack.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("deleted")
        val deleted: Boolean,
        @JsonProperty("real_name")
        val realName: String?,
        @JsonProperty("is_restricted")
        val isRestricted: Boolean,
        @JsonProperty("is_ultra_restricted")
        val isUltraRestricted: Boolean,
        @JsonProperty("is_bot")
        val isBot: Boolean,
        @JsonProperty("is_app_user")
        val isAppUser: Boolean,
        @JsonProperty("profile")
        val profile: UserProfileDto
)