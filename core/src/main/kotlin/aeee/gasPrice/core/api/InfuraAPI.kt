package aeee.gasPrice.core.api

import aeee.gasPrice.core.vo.GasPrice
import reactor.core.publisher.Mono

interface InfuraAPI {

    fun getEth_getBlockByNumber(): Mono<GasPrice>
}