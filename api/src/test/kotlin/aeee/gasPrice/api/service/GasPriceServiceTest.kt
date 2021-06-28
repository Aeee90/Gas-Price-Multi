package aeee.gasPrice.api.service

import aeee.gasPrice.api.SpeedTime
import aeee.gasPrice.core.api.InfuraAPI
import aeee.gasPrice.core.util.UnitConvertor
import aeee.gasPrice.core.entity.GasPrice
import aeee.gasPrice.core.entity.Transaction
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.util.*

@SpringBootTest
class GasPriceServiceTest {

    @Autowired
    private lateinit var gasPriceService: GasPriceService

    @Autowired
    private lateinit var infuraApi: InfuraAPI

    @Test
    @DisplayName("test Logic")
    fun testLogic() {
        val gasPrice = GasPrice().apply {
            result = aeee.gasPrice.core.entity.Result().apply {
                number = BigDecimal(12617125)
                transactions = arrayListOf(
                    Transaction(BigDecimal(3000000000L)),
                    Transaction(BigDecimal(3000000000L)),
                    Transaction(BigDecimal(3000000000L)),
                    Transaction(BigDecimal(2000000000L)),
                    Transaction(BigDecimal(2000000000L)),
                    Transaction(BigDecimal(1000000000L))
                )
            }
        }

        StepVerifier.create(gasPriceService.manufactureGasPrice(gasPrice))
            .assertNext {
                val transactionCounters = it.transactionCounter
                it.number == BigDecimal(12617125)
                && it.size == 6L
                && it.average == BigDecimal("2.3")
                && it.max == BigDecimal("3.0")
                && it.min == BigDecimal("1.0")

                && transactionCounters[0].gasPrice == BigDecimal("1.0")
                && transactionCounters[0].count == 1L

                && transactionCounters[1].gasPrice == BigDecimal("2.0")
                && transactionCounters[1].count == 2L

                && transactionCounters[2].gasPrice == BigDecimal("3.0")
                && transactionCounters[2].count == 3L
            }
            .verifyComplete()
    }

    @Test
    @DisplayName("test Average Speed Less Than 1000ms")
    fun testAverageSpeedLessThan1000ms() {
        val testData: List<Mono<GasPrice>> = Arrays.asList(
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber(),
            this.infuraApi.getEth_getBlockByNumber()
        )
        var sum: Long = 0
        for (gasPrice in testData) {
            sum += SpeedTime.measure("count logic") {
                gasPrice.flatMap(gasPriceService::manufactureGasPrice)
            }
        }

        assert(sum / testData.size < 1000)
    }
}