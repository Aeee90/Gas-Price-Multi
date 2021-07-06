package aeee.gasPrice.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication(
    scanBasePackages = ["aeee.gasPrice"]
)
@RefreshScope
class GasPriceApi

fun main(args: Array<String>){
    runApplication<GasPriceApi>(*args)
}