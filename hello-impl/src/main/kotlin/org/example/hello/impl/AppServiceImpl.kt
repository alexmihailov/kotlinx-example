@file:OptIn(ExperimentalSerializationApi::class)

package org.example.hello.impl

import akka.NotUsed
import akka.actor.ActorSystem
import com.lightbend.lagom.javadsl.api.ServiceCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.encodeToConfig
import org.example.hello.api.AppService
import org.example.hello.api.MessageData
import org.taymyr.lagom.javadsl.api.CoroutineService
import javax.inject.Inject

class AppServiceImpl @Inject constructor(
    actorSystem: ActorSystem,
    private val appConfig: AppConfig
) : AppService, CoroutineService {

    override val dispatcher: CoroutineDispatcher = actorSystem.dispatcher.asCoroutineDispatcher()

    override fun hello(name: String): ServiceCall<NotUsed, MessageData> = serviceCall {
        val message = MessageData(
            message = "Hello, $name!",
            config = "Config: $appConfig",
            hoconConfig = "Hocon config: ${Hocon.encodeToConfig(appConfig)}"
        )
        message
    }
}
