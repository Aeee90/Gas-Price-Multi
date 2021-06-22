package aeee.gasPrice.core.api

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InfuraAPITest {

    @Autowired
    private lateinit var infuraAPI: InfuraAPI

    @Test
    @DisplayName("test connection Infura API server")
    fun testConnectionInfuraAPI() {
        val gasPrice = infuraAPI.getEth_getBlockByNumber()

        println(gasPrice)

        Assertions.assertNotNull(gasPrice)

    }
}