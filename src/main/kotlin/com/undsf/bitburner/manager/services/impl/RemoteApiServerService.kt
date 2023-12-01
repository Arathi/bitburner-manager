package com.undsf.bitburner.manager.services.impl

import com.undsf.bitburner.manager.endpoints.RemoteApiServerEndpoint
import com.undsf.bitburner.manager.jsonrpc.messages.Request
import com.undsf.bitburner.manager.services.JsonRpcServerService
import com.undsf.bitburner.manager.services.RemoteApi
import jakarta.websocket.Session
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

private val logger = LoggerFactory.getLogger(RemoteApiServerService::class.java)

@Service("remote-api-server-svc")
class RemoteApiServerService: JsonRpcServerService(), RemoteApi {
    val sequence: AtomicLong = AtomicLong(0)
    var endpoint: RemoteApiServerEndpoint? = null
    val session: Session
        get() = endpoint?.session ?: throw RuntimeException("WebSocket会话获取失败！")

    override fun pushFile(filename: String, content: String, server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "pushFile",
            params = mapOf(
                "filename" to filename,
                "content" to content,
                "server" to server
            )
        )
        send(request)
    }

    override fun getFile(filename: String, server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "getFile",
            params = mapOf(
                "filename" to filename,
                "server" to server
            )
        )
        send(request)
    }

    override fun deleteFile(filename: String, server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "deleteFile",
            params = mapOf(
                "filename" to filename,
                "server" to server
            )
        )
        send(request)
    }

    override fun getFileNames(server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "getFileNames",
            params = mapOf(
                "server" to server
            )
        )
        send(request)
    }

    override fun getAllFiles(server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "getAllFiles",
            params = mapOf(
                "server" to server
            )
        )
        send(request)
    }

    override fun calculateRam(filename: String, server: String) {
        val request = Request(
            id = sequence.incrementAndGet(),
            method = "calculateRam",
            params = mapOf(
                "filename" to filename,
                "server" to server
            )
        )
        send(request)
    }

    override fun getDefinitionFile() {
        val request = Request<Map<String, Any>>(
            id = sequence.incrementAndGet(),
            method = "getDefinitionFile",
        )
        send(request)
    }

    override fun send(request: Request<*>) {
        super.send(request)
        val json = objectMapper.writeValueAsString(request)
        logger.info("发送请求报文：\n${json}")
        session.asyncRemote.sendText(json)
    }
}
