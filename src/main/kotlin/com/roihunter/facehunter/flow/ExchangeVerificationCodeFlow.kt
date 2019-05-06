package com.roihunter.facehunter.flow

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.roihunter.facehunter.dto.slack.auth.AuthResponseDto
import com.roihunter.facehunter.entity.Workspace
import com.roihunter.facehunter.manager.WorkspaceManager
import khttp.post
import khttp.structures.authorization.BasicAuthorization
import org.springframework.stereotype.Service

/**
 * https://api.slack.com/docs/oauth
 */
@Service
class ExchangeVerificationCodeFlow(
        private val mapper: ObjectMapper,
        private val workspaceManager: WorkspaceManager
) {

    private val clientId = System.getenv("SLACK_CLIENT_ID")
    private val clientSecret = System.getenv("SLACK_CLIENT_SECRET")

    fun handleVerification(code: String): String {
        val oauthAccessResponse = post(
                url = "https://slack.com/api/oauth.access",
                headers = mapOf("content-type" to "application/x-www-form-urlencoded"),
                params = mapOf("code" to code, "redirect_uri" to "https://face.roihunter.com/oauth/redirect"),
                auth = BasicAuthorization(clientId, clientSecret)
        )
        val authResponseDto: AuthResponseDto = mapper.readValue(oauthAccessResponse.text)
        workspaceManager.storeWorkspace(Workspace(
                accessToken = authResponseDto.accessToken,
                teamName = authResponseDto.teamName,
                teamId = authResponseDto.teamId,
                botId = authResponseDto.bot.botUserId,
                botToken = authResponseDto.bot.botToken
        ))
        return if (authResponseDto.ok) {
            "Face Hunter authenticated for team with name ${authResponseDto.teamName}"
        } else {
            "Authentication failed."
        }
    }
}