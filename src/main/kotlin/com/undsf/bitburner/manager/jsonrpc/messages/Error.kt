package com.undsf.bitburner.manager.jsonrpc.messages

data class Error(
    var code: Int,
    var message: String,
    var data: Any,
)