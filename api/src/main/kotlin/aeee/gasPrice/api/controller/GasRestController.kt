package aeee.gasPrice.api.controller

import aeee.gasPrice.api.service.GasPriceService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import reactor.core.publisher.Mono


@Configuration
class GasRestController(
    private val gasPriceService: GasPriceService
) {

    @Bean
    fun personRouter() = router {
        GET("/gasprice", RequestPredicates.accept(MediaType.APPLICATION_JSON), { _ -> gasPriceService.manufactureGasPrice() })
    }
}
