package com.roihunter.facehunter.manager

import com.roihunter.facehunter.entity.Workspace
import com.roihunter.facehunter.repository.WorkspaceRepository
import org.springframework.stereotype.Service

/**
 * Handles workspace data storage and retrieval.
 */
@Service
class WorkspaceManager(
        private val workspaceRepository: WorkspaceRepository
) {

    fun loadAllWorkspaces(): List<Workspace> {
        return workspaceRepository.findAll()
    }

    fun storeWorkspace(workspace: Workspace) {
        workspaceRepository.save(workspace)
    }

    fun getWorkspaceByTeamId(teamId: String): Workspace {
        val workspaceOptional = workspaceRepository.getByTeamId(teamId)
        if (workspaceOptional.isPresent) {
            return workspaceOptional.get()
        } else {
            throw IllegalArgumentException("Workspace with given team id is not authenticated for Face Hunter: $teamId")
        }
    }

}