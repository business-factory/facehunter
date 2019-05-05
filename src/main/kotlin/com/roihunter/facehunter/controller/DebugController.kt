package com.roihunter.facehunter.controller

import com.roihunter.facehunter.entity.Workspace
import com.roihunter.facehunter.manager.WorkspaceManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DebugController(
        private val workspaceManager: WorkspaceManager
) {

    @PostMapping(value = ["/debug/workspace"])
    fun testWorkspace() {
        println(workspaceManager.loadAllWorkspaces())
    }
}