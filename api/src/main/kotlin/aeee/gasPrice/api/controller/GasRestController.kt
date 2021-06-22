package aeee.gasPrice.api.controller

import aeee.gasPrice.api.dto.BlockInfoDTO
import aeee.gasPrice.api.service.GasPriceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gasprice")
class GasRestController(
    private val gasPriceService: GasPriceService
) {

    @GetMapping
    fun getBlockInfo(): BlockInfoDTO  = gasPriceService.manufactureGasPrice()
}
