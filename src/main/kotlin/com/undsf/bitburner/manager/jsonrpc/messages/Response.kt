package com.undsf.bitburner.manager.jsonrpc.messages

import com.fasterxml.jackson.annotation.JsonInclude

data class Response<R>(
    var jsonrpc: String = JsonRpcVersion,
    var id: Long,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var result: R? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var error: Error? = null,
)