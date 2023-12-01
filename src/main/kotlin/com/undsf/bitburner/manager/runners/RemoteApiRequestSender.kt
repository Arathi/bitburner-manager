package com.undsf.bitburner.manager.runners

import com.undsf.bitburner.manager.messages.RemoteApiRequest
import com.undsf.bitburner.manager.services.RemoteApiRequestQueue
import com.undsf.bitburner.manager.services.impl.RemoteApiServerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@Order(0)
@EnableScheduling
class RemoteApiRequestSender: ApplicationRunner {
    @Autowired
    lateinit var queueSvc: RemoteApiRequestQueue

    @Autowired
    lateinit var serverSvc: RemoteApiServerService

    @Scheduled(cron = "* * * * * *")
    fun scheduler() {
        var request: RemoteApiRequest?
        do {
            request = queueSvc.poll()
            if (request != null) {
                serverSvc.send(request)
            }
        }
        while (request != null)
    }

    override fun run(args: ApplicationArguments?) {
    }
}