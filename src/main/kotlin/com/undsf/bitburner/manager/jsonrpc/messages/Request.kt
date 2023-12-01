package com.undsf.bitburner.manager.jsonrpc.messages

import com.fasterxml.jackson.annotation.JsonInclude

const val JsonRpcVersion = "2.0"

data class Request<P>(
    var jsonrpc: String = JsonRpcVersion,
    var id: Long,
    var method: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var params: P? = null,
)
