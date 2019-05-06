package com.roihunter.facehunter.flow

import com.roihunter.facehunter.dto.slack.block.ActionsBlockDto
import com.roihunter.facehunter.dto.slack.block.ButtonBlockDto
import com.roihunter.facehunter.dto.slack.block.DividerBlockDto
import com.roihunter.facehunter.dto.slack.block.SectionBlockDto
import com.roihunter.facehunter.dto.slack.block.StructuredBlock
import com.roihunter.facehunter.manager.SlackManager
import com.roihunter.facehunter.manager.WorkspaceManager
import org.springframework.stereotype.Service

@Service
class EvaluateGuessFlow(
        private val slackManager: SlackManager,
        private val workspaceManager: WorkspaceManager
) {

    fun evaluateGuess(correct: Boolean, correctName: String, position: String, userId: String, teamId: String) {
        val workspace = workspaceManager.getWorkspaceByTeamId(teamId)
        val message = if (correct) {
            ":heavy_check_mark: Position: $position"
        } else {
            ":negative_squared_cross_mark: It was $correctName. Position: $position"
        }

        val blocks = mutableListOf<StructuredBlock>()
        blocks.add(SectionBlockDto(content = message))
        blocks.add(DividerBlockDto())
        blocks.add(ActionsBlockDto(elements = listOf(ButtonBlockDto(text = ":heavy_plus_sign: Guess another", value = "next"))))

        slackManager.postSlackMessage(userId, workspace.botToken, blocks)
    }
}