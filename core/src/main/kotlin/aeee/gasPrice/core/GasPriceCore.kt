package aeee.gasPrice.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GasPriceCore

fun main(args: Array<String>){
    runApplication<GasPriceCore>(*args)
}