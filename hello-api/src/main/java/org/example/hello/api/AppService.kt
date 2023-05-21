package org.example.hello.api

import akka.NotUsed
import com.lightbend.lagom.javadsl.api.Descriptor
import com.lightbend.lagom.javadsl.api.Service
import com.lightbend.lagom.javadsl.api.Service.named
import com.lightbend.lagom.javadsl.api.Service.restCall
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.transport.Method
import org.example.hello.api.KSerializerSettings.json
import org.taymyr.lagom.javadsl.api.withResponseKotlinJsonSerializer
import kotlin.reflect.jvm.javaMethod

interface AppService : Service {

    fun hello(name: String): ServiceCall<NotUsed, MessageData>

    override fun descriptor(): Descriptor = named("hello").withCalls(
        restCall<NotUsed, MessageData>(
            Method.GET,
            "/api/hello/:name",
            AppService::hello.javaMethod
        ).withResponseKotlinJsonSerializer(json)
            .withAutoAcl(true)
    )
}