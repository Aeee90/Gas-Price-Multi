package aeee.gasPrice.core.api

import aeee.gasPrice.core.entity.GasPrice
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class InfuraAPITest {

    @Autowired
    private lateinit var infuraAPI: InfuraAPI

    @Test
    @DisplayName("test connection Infura API server")
    fun testConnectionInfuraAPI() {
        val gasPrice = infuraAPI.getEth_getBlockByNumber()

        StepVerifier.create(gasPrice)
            .assertNext { gasPrice -> GasPrice.Empty != gasPrice }
            .expectComplete()
            .verify()
    }
}