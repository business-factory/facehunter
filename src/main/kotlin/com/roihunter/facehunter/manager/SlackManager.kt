package com.roihunter.facehunter.manager

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.roihunter.facehunter.dto.slack.block.StructuredBlock
import com.roihunter.facehunter.dto.slack.channel.OpenChannelDto
import com.roihunter.facehunter.dto.slack.user.AllUsersDto
import com.roihunter.facehunter.dto.slack.user.UserDto
import com.roihunter.facehunter.exception.RequestNotVerifiedException
import khttp.get
import khttp.post
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Service
class SlackManager(
        private val mapper: ObjectMapper
) {

    private val signingSecret = System.getenv("SLACK_SIGNING_SECRET")

    fun postSlackMessage(userId: String, botToken: String, blocks: List<StructuredBlock>? = null, text: String = "") {
        val channelId = getImChannelIdForUserId(userId, botToken)
        val params = mutableMapOf(
                "channel" to channelId,
                "text" to text,
                "token" to botToken
        )
        if (blocks != null) { // Some messages are simple - no need to include blocks in there.
            params["blocks"] = mapper.writeValueAsString(blocks)
        }
        post(
                url = "https://slack.com/api/chat.postMessage",
                params = params
        )
    }

    /**
     * Posts an ephemeral message (only visible to the user to the given channel. Shouldn't use bot token, but rather app token.
     */
    fun postEphemeralMessage(userId: String, channelId: String, token: String, text: String) {
        post(
                url = "https://slack.com/api/chat.postEphemeral",
                params = mutableMapOf(
                        "channel" to channelId,
                        "text" to text,
                        "token" to token,
                        "user" to userId
                )
        )
    }

    /**
     * Retrieves all users in given Slack workspace.
     */
    fun getAllUsersInWorkspace(botToken: String): List<UserDto> {
        val allUsersResponse = get(
                url = "https://slack.com/api/users.list",
                params = mapOf("token" to botToken)
        )
        val allUsersDto: AllUsersDto = mapper.readValue(allUsersResponse.text)
        return allUsersDto.members
    }

    /**
     * Retrieves IM channel based on the user id.
     */
    private fun getImChannelIdForUserId(userId: String, botToken: String): String {
        val imOpenResponse = post(
                url = "https://slack.com/api/im.open",
                params = mapOf(
                        "token" to botToken,
                        "user" to userId
                )
        )
        val imChannelDto: OpenChannelDto = mapper.readValue(imOpenResponse.text)
        return imChannelDto.channel.id
    }

    /**
     * Verifies that given message really came from Slack. Doesn't check for timestamp age. Throws an exception if it the signature doesn't match.
     */
    fun verifyRequest(requestBody: String, timestamp: String, slackSignature: String) {
        val algorithm = "HmacSHA256"
        val hashKey = SecretKeySpec(signingSecret.toByteArray(), algorithm)
        val mac: Mac = Mac.getInstance(algorithm)
        mac.init(hashKey)

        val sigBaseString = "v0:$timestamp:$requestBody"
        val digestMessage = mac.doFinal(sigBaseString.toByteArray()).fold("") { str, it -> str + "%02x".format(it) }
        val fullSignature = "v0=$digestMessage"

        if (fullSignature != slackSignature) {
            throw RequestNotVerifiedException()
        }
    }
}