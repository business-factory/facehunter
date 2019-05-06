package com.roihunter.facehunter.dto.slack.user

data class AllUsersDto(
        val ok: Boolean,
        val members: List<UserDto>
)