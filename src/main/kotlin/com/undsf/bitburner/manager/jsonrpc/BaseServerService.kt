package com.undsf.bitburner.manager.jsonrpc

import com.undsf.bitburner.manager.jsonrpc.messages.Request
import com.undsf.bitburner.manager.jsonrpc.messages.Response

class BaseServerService {
    val requests = mutableMapOf<Long, Request<*>>()
    val responses = mutableMapOf<Long, Response<*>>()

    fun send(request: Request<*>) {
        requests[request.id] = request
    }
}