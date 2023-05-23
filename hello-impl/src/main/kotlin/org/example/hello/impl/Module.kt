package org.example.hello.impl

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport
import com.typesafe.config.Config
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import org.example.hello.api.AppService

class Module : AbstractModule(), ServiceGuiceSupport {

    override fun configure() {
        bindService(AppService::class.java, AppServiceImpl::class.java)
    }

    @Provides
    @OptIn(ExperimentalSerializationApi::class)
    fun provideAppConfig(conf: Config): AppConfig =
        Hocon.decodeFromConfig(conf.getConfig("app-config"))
}
