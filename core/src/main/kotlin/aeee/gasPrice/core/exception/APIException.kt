package aeee.gasPrice.core.exception

import org.springframework.http.HttpStatus

class APIException(
    val httpStatus: HttpStatus
): RuntimeException(httpStatus.reasonPhrase) {

}