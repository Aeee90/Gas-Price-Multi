package aeee.gasPrice.core.api.config

import aeee.gasPrice.core.exception.InfuraErrorException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.Series
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import java.io.IOException

@Component("InfuraAPIHttpSender")
internal class InfuraAPIHttpSender : HttpSender {

    constructor(@Value("\${infura.api.url}") uri: String): super(uri)

    override fun setHeader(headers: HttpHeaders) = headers.apply {
            contentType = MediaType.APPLICATION_JSON
    }

    override fun hasError(response: ClientHttpResponse): Boolean {
        return try {
            val status = response.statusCode.series()
            status == Series.CLIENT_ERROR || status == Series.SERVER_ERROR
        } catch (e: IOException) {
            true
        }
    }

    override fun handleError(response: ClientHttpResponse) {
        throw InfuraErrorException(response)
    }
}