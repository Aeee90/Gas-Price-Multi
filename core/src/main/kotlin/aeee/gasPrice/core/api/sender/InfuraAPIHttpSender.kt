package aeee.gasPrice.core.api.sender

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component("InfuraAPIHttpSender")
internal class InfuraAPIHttpSender : HttpSender {

    constructor(@Value("\${infura.api.url}") uri: String): super(uri)

    override fun setHeader(headers: HttpHeaders) = headers.apply {
            contentType = MediaType.APPLICATION_JSON
    }
}