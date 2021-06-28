package aeee.gasPrice.core.api

import aeee.gasPrice.core.entity.GasPrice
import reactor.core.publisher.Mono

interface InfuraAPI {

    fun getEth_getBlockByNumber(): Mono<GasPrice>
}