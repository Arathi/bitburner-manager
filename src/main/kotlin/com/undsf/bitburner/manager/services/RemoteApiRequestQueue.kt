package com.undsf.bitburner.manager.services

import com.undsf.bitburner.manager.messages.RemoteApiRequest

interface RemoteApiRequestQueue {
    /**
     * 推送请求报文到队列
     */
    fun push(request: RemoteApiRequest): Boolean

    /**
     * 从队列获取请求报文
     */
    fun poll(): RemoteApiRequest?
}