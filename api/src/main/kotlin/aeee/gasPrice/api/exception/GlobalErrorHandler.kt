package aeee.gasPrice.api.exception

import aeee.gasPrice.core.exception.APIException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Configuration
@Order(-2)
class GlobalErrorHandler(
    errorAttributes: ErrorAttributes, resourceProperties: ResourceProperties, applicationContext: ApplicationContext, configurer: ServerCodecConfigurer
): AbstractErrorWebExceptionHandler(errorAttributes, resourceProperties, applicationContext) {

    private companion object {
        val logger = LoggerFactory.getLogger(GlobalErrorHandler::class.java)
    }

    init {
        this.setMessageWriters(configurer.writers);
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(RequestPredicates.all()) { request ->
                val error = errorAttributes.getError(request);

                if(error is APIException) ServerResponse.status(error.httpStatus).build()
                else ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }
    }

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        logger.error("{}", ex)
        return exchange.response.writeWith(
            Mono.just(exchange.response.bufferFactory().wrap(ex.message?.toByteArray() ?: "".toByteArray()))
        )
    }
}