package aeee.gasPrice.confsvr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfsvrApplication

fun main(args: Array<String>){
    runApplication<ConfsvrApplication>(*args)
}