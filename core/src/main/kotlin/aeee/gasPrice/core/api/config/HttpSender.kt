package aeee.gasPrice.core.api.config

import org.springframework.http.HttpHeaders
import org.springframework.lang.Nullable
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono
import java.net.URI
import java.util.function.Consumer


abstract class HttpSender(uri: URI) : ResponseErrorHandler {

    private val webClient: WebClient
    constructor(uri: String): this(URI.create(uri))

    init {
        webClient = WebClient.builder()
        .baseUrl(uri.toString())
        .defaultHeaders(_setHeader())
        .build()
    }

    private fun _setHeader(): Consumer<HttpHeaders> = Consumer { setHeader(it) }

    protected abstract fun setHeader(headers: HttpHeaders): HttpHeaders

    open fun <T> post(@Nullable request: Any, clazz: Class<T>): Mono<T> = webClient
            .post()
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(clazz)
}