package aeee.gasPrice.api.service

import aeee.gasPrice.api.dto.BlockInfoDTO
import aeee.gasPrice.core.entity.GasPrice
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
interface GasPriceService {
    fun manufactureGasPrice(): Mono<ServerResponse>
    fun manufactureGasPrice(gasPriceVO: GasPrice): BlockInfoDTO
}