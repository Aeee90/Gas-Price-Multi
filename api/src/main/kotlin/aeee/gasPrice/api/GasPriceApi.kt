package aeee.gasPrice.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GasPriceApi

fun main(args: Array<String>){
    runApplication<GasPriceApi>(*args)
}