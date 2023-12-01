package com.undsf.bitburner.manager.services.mock

import com.undsf.bitburner.manager.messages.RemoteApiRequest
import com.undsf.bitburner.manager.services.RemoteApiRequestQueue
import org.springframework.stereotype.Service
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Service("remote-api-req-queue-mock")
class RemoteApiRequestQueueMock: RemoteApiRequestQueue {
    val queue: BlockingQueue<RemoteApiRequest> = ArrayBlockingQueue(10000)

    override fun push(request: RemoteApiRequest): Boolean {
        val succ = queue.add(request)
        return succ
    }

    override fun poll(): RemoteApiRequest? {
        val request = queue.poll()
        return request
    }
}