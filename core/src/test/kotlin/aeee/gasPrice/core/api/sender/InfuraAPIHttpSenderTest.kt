package aeee.gasPrice.core.api.sender

import aeee.gasPrice.core.entity.GasPrice
import aeee.gasPrice.core.enums.InfuraMethod
import aeee.gasPrice.core.exception.APIException
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class InfuraAPIHttpSenderTest {

    @Autowired
    private lateinit var infuraAPIHttpSender: InfuraAPIHttpSender

    @Test
    @DisplayName("test that Webclient throw APIException" )
    fun testThatWebclientThrowAPIException() {
        val params = JSONObject()
        params.put("jsonrpc1", "2.0")
        params.put("method", InfuraMethod.ETH_GET_BLOCK_BY_NUMBER)
        params.put("id", 1)
        params.put("params", JSONArray().apply {
            put("wrongParam")
            put(true)
        })

        StepVerifier.create(infuraAPIHttpSender.post(params, GasPrice::class.java))
            .consumeErrorWith { throwable -> throwable is APIException }
            .verify()

    }

}