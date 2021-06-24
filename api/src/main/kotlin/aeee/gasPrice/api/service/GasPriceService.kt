package aeee.gasPrice.api.service

import aeee.gasPrice.api.dto.BlockInfoDTO
import aeee.gasPrice.core.vo.GasPrice
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface GasPriceService {
    fun manufactureGasPrice(): Mono<BlockInfoDTO>
    fun manufactureGasPrice(gasPriceVO: GasPrice): Mono<BlockInfoDTO>
}