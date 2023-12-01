package com.undsf.bitburner.manager.configurations

import jakarta.servlet.ServletContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.server.standard.ServerEndpointExporter

private val logger = LoggerFactory.getLogger(WebSocketConfiguration::class.java)

@Configuration
class WebSocketConfiguration: ServletContextInitializer {
    @Value("\${bitburner.websocket.text-buffer-size:1048576}")
    var textBufferSize: Long = 1048576

    @Value("\${bitburner.websocket.binary-buffer-size:1048576}")
    var binaryBufferSize: Long = 1048576

    @Bean
    fun buildServerEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }

    override fun onStartup(servletContext: ServletContext?) {
        if (servletContext != null) {
            servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize", textBufferSize.toString())
            servletContext.setInitParameter("org.apache.tomcat.websocket.binaryBufferSize", binaryBufferSize.toString())
        }
        else {
            logger.warn("初始化出错")
        }
    }
}