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
class SendHelpInfoFlow(
        private val slackManager: SlackManager,
        private val workspaceManager: WorkspaceManager
) {

    fun sendHelpInfo(userId: String, teamId: String) {
        val workspace = workspaceManager.getWorkspaceByTeamId(teamId)

        val blocks = mutableListOf<StructuredBlock>()
        blocks.add(SectionBlockDto(
                content = "In order to get help for Face Hunter, please visit our GitHub at https://github.com/business-factory/facehunter/tree/master/support")
        )
        blocks.add(DividerBlockDto())
        blocks.add(ActionsBlockDto(elements = listOf(
                ButtonBlockDto(text = ":heavy_plus_sign: Guess another", value = "next")
        )))

        slackManager.postSlackMessage(userId, workspace.botToken, blocks)
    }
}