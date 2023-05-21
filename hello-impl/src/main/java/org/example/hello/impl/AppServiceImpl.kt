package org.example.hello.impl

import akka.NotUsed
import com.lightbend.lagom.javadsl.api.ServiceCall
import org.example.hello.api.AppService
import java.util.concurrent.CompletableFuture

class AppServiceImpl : AppService {

    override fun hello(name: String): ServiceCall<NotUsed, String> = ServiceCall {
        CompletableFuture.completedFuture("Hello, $name")
    }
}
