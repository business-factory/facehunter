package com.roihunter.facehunter.flow

import com.roihunter.facehunter.dto.slack.block.ActionsBlockDto
import com.roihunter.facehunter.dto.slack.block.ButtonBlockDto
import com.roihunter.facehunter.dto.slack.block.ImageBlockDto
import com.roihunter.facehunter.dto.slack.block.SectionBlockDto
import com.roihunter.facehunter.dto.slack.block.StructuredBlock
import com.roihunter.facehunter.dto.slack.user.UserDto
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
        val guessableColleagues = employeeList.filter {
            it.profile != null && !it.deleted && !it.isAppUser && !it.isBot && !it.isRestricted && !it.isUltraRestricted && it.id != userId
                    && it.profile.isCustomImage && it.profile.getImageUrl() != null
        }.shuffled() // There is no smart randomness, i.e. the guesses can repeat.
        if (guessableColleagues.size < 6) {
            slackManager.postSlackMessage(
                    userId, workspace.botToken, text = "Sorry, you don't have enough employees eligible for guessing to run Face Hunter. You need at least 6."
            )
            return // No reason to continue.
        }
        val correctGuess = guessableColleagues.first()
        val wrongGuesses = guessableColleagues.drop(1).take(4)

        val blocks = mutableListOf<StructuredBlock>()
        blocks.add(ImageBlockDto(altText = "image to guess", imageUrl = correctGuess.profile?.getImageUrl() ?: throw IllegalStateException("Missing image.")))
        blocks.add(SectionBlockDto(content = "Guess who it is!"))

        val buttons = mutableListOf(employeeToButton(correctGuess, correctGuess, "correct"))
        buttons.addAll(wrongGuesses.map { employeeToButton(it, correctGuess, "wrong") })
        buttons.shuffle()
        blocks.add(ActionsBlockDto(elements = buttons))

        slackManager.postSlackMessage(userId, workspace.botToken, blocks)
    }

    private fun employeeToButton(user: UserDto, correctUser: UserDto, correct: String = "wrong"): ButtonBlockDto {
        val buttonName = user.realName ?: user.profile?.realName ?: user.name
        val correctName = correctUser.realName ?: correctUser.profile?.realName ?: correctUser.name
        return ButtonBlockDto(
                text = buttonName,
                value = "$correct..$correctName..${correctUser.profile?.title}"
        )
    }
}