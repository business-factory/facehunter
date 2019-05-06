package com.roihunter.facehunter.flow

import com.roihunter.facehunter.manager.SlackManager
import com.roihunter.facehunter.manager.WorkspaceManager
import org.springframework.stereotype.Service

/**
 * Posts a new guess to given user.
 */
@Service
class NewGuessFlow(
        private val workspaceManager: WorkspaceManager,
        private val slackManager: SlackManager
) {

    /**
     * Sends a new guess to the specified user.
     */
    fun newGuess(userId: String, teamId: String) {
        val workspace = workspaceManager.getWorkspaceByTeamId(teamId)
        val employeeList = slackManager.getAllUsersInWorkspace(workspace.botToken)
        slackManager.postSlackMessage(userId, workspace.botToken)
    }
}