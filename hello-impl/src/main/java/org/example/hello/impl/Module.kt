package org.example.hello.impl

import com.google.inject.AbstractModule
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport
import org.example.hello.api.AppService

class Module : AbstractModule(), ServiceGuiceSupport {

    override fun configure() {
        bindService(AppService::class.java, AppServiceImpl::class.java)
    }
}
