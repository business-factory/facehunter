package com.roihunter.facehunter.dto.slack.block

/**
 * We only support list of buttons.
 */
data class ActionsBlockDto(
        val type: String = "actions",
        val elements: List<ButtonBlockDto>
) : StructuredBlock