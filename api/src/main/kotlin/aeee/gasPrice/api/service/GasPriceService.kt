package aeee.gasPrice.api.service

import aeee.gasPrice.api.dto.BlockInfoDTO
import aeee.gasPrice.core.vo.GasPrice
import org.springframework.stereotype.Service

@Service
interface GasPriceService {
    fun manufactureGasPrice(): BlockInfoDTO
    fun manufactureGasPrice(gasPriceVO: GasPrice): BlockInfoDTO
}