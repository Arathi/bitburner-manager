package com.undsf.bitburner.manager.controllers

import com.undsf.bitburner.manager.services.impl.RemoteApiServerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/remote")
class RemoteApiController {
    @Autowired
    lateinit var serverSvc: RemoteApiServerService

    @PostMapping("/{server}/files")
    fun pushFile(
        @PathVariable server: String,
        @RequestParam("path") path: String,
        @RequestBody content: String
    ): String {
        return "OK"
    }

    @GetMapping("/{server}/files")
    fun getFile(
        @PathVariable server: String,
        @RequestParam("path") path: String
    ): String {
        serverSvc.getFile(path, server)
        return "OK"
    }

    @GetMapping("/{server}/filenames")
    fun getFileNames(@PathVariable server: String): String {
        serverSvc.getFileNames(server)
        return "OK"
    }

    @GetMapping("/{server}/calculateRam")
    fun calculateRam(
        @PathVariable server: String,
        @RequestParam("path") path: String
    ): String {
        serverSvc.calculateRam(path, server)
        return "OK"
    }

    @GetMapping("/definitionFile")
    fun getDefinitionFile(): String {
        serverSvc.getDefinitionFile()
        return "OK"
    }
}