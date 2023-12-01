package com.undsf.bitburner.manager.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.undsf.bitburner.manager.jsonrpc.messages.JsonRpcVersion
import com.undsf.bitburner.manager.jsonrpc.messages.Request
import com.undsf.bitburner.manager.jsonrpc.messages.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

private val logger = LoggerFactory.getLogger(JsonRpcServerService::class.java)

abstract class JsonRpcServerService {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    val requests = mutableMapOf<Long, Request<*>>()
    val responses = mutableMapOf<Long, Response<*>>()

    open fun send(request: Request<*>) {
        requests[request.id] = request
    }

    open fun saveRequest(request: Request<*>) {
        requests[request.id] = request
    }

    open fun saveResponse(response: Response<*>) {
        responses[response.id] = response
    }
}