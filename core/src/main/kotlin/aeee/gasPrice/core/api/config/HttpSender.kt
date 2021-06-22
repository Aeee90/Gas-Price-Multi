package aeee.gasPrice.core.api.config

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.lang.Nullable
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.net.URI

abstract class HttpSender: ResponseErrorHandler {

    private val uri: URI;
    private var headers: HttpHeaders = HttpHeaders()
    private val restTemplate = RestTemplate()

    constructor(uri: URI) { this.uri = uri }

    constructor(uri: String) { this.uri = URI.create(uri) }


    protected open fun _setHeader() {
        headers = setHeader(headers)
    }

    protected abstract fun setHeader(headers: HttpHeaders): HttpHeaders


    open fun <T> getHttpEntity(data: T): HttpEntity<T> {
        return HttpEntity(data, headers)
    }

    protected open fun <T> getHttpEntity(data: T, customizeHttpHeader: CustomizeHttpHeader): HttpEntity<T> {
        val newHeaders = setHeader(HttpHeaders())
        return HttpEntity(data, customizeHttpHeader.customizeHttpHeaders(newHeaders))
    }

    open fun <T> post(@Nullable request: Any, clazz: Class<T>): T? {
        return restTemplate.postForEntity(uri, request, clazz).body
    }

    protected interface CustomizeHttpHeader {
        fun customizeHttpHeaders(headers: HttpHeaders): HttpHeaders
    }
}