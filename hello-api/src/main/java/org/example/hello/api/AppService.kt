package org.example.hello.api

import akka.NotUsed
import com.lightbend.lagom.javadsl.api.Descriptor
import com.lightbend.lagom.javadsl.api.Service
import com.lightbend.lagom.javadsl.api.Service.named
import com.lightbend.lagom.javadsl.api.Service.restCall
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.transport.Method
import kotlin.reflect.jvm.javaMethod

interface AppService : Service {

    fun hello(name: String): ServiceCall<NotUsed, String>

    override fun descriptor(): Descriptor = named("hello").withCalls(
        restCall<NotUsed, String>(
            Method.GET,
            "/api/hello/:name",
            AppService::hello.javaMethod
        ).withAutoAcl(true)
    )
}