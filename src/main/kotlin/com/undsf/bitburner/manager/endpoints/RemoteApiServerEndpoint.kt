package com.undsf.bitburner.manager.endpoints

import com.undsf.bitburner.manager.services.impl.RemoteApiServerService
import jakarta.websocket.CloseReason
import jakarta.websocket.OnClose
import jakarta.websocket.OnError
import jakarta.websocket.OnMessage
import jakarta.websocket.OnOpen
import jakarta.websocket.Session
import jakarta.websocket.server.ServerEndpoint
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.ByteBuffer

private val logger = LoggerFactory.getLogger(RemoteApiServerEndpoint::class.java)

@ServerEndpoint("/")
@Component
class RemoteApiServerEndpoint {
    lateinit var session: Session

    @set:Autowired
    var remoteApiServerSvc: RemoteApiServerService
        get() = RemoteApiServerEndpoint.remoteApiServerSvc
        set(svc) {
            RemoteApiServerEndpoint.remoteApiServerSvc = svc
        }

    @OnOpen
    fun onOpen(session: Session) {
        logger.info("接收到WebSocket连接：${session.id}")
        this.session = session
        remoteApiServerSvc.endpoint = this
    }

    @OnMessage
    fun onMessage(session: Session, message: String) {
        logger.info("接收到WebSocket服务端（${session.id}）接收到文本报文：${message}")
    }

    @OnMessage
    fun onMessage(session: Session, message: ByteBuffer) {
        logger.info("接收到WebSocket服务端（${session.id}）接收到二进制报文：${message}")
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        logger.warn("WebSocket服务端（${session.id}）出错", throwable)
    }

    @OnClose
    fun onClose(session: Session, closeReason: CloseReason) {
        logger.info("关闭WebSocket连接：${session.id}")
        remoteApiServerSvc.endpoint = null
    }

    companion object {
        lateinit var remoteApiServerSvc: RemoteApiServerService
    }
}