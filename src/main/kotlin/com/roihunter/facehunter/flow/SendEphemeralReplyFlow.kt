package com.roihunter.facehunter.flow

import com.roihunter.facehunter.manager.SlackManager
import com.roihunter.facehunter.manager.WorkspaceManager
import org.springframework.stereotype.Service

@Service
class SendEphemeralReplyFlow(
        private val workspaceManager: WorkspaceManager,
        private val slackManager: SlackManager
) {

    fun sendEphemeral(userId: String, channelId: String, teamId: String, text: String) {
        val workspace = workspaceManager.getWorkspaceByTeamId(teamId)
        slackManager.postEphemeralMessage(userId, channelId, workspace.accessToken, text)
    }
}