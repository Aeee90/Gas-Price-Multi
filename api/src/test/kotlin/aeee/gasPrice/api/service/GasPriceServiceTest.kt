package aeee.gasPrice.api.service

import aeee.gasPrice.api.SpeedTime
import aeee.gasPrice.core.api.InfuraAPI
import aeee.gasPrice.core.util.UnitConvertor
import aeee.gasPrice.core.vo.GasPrice
import aeee.gasPrice.core.vo.Transaction
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.*

@SpringBootTest
class GasPriceServiceTest {

    @Autowired
    private lateinit var gasPriceService: GasPriceService

    @Autowired
    private lateinit var infuraApi: InfuraAPI

    @Test
    fun testToConvert16to10() {
        assert(
            UnitConvertor.convertHexStrToDecimalBigDecimal("0x104c533c00") == BigDecimal("70000000000")
        )
    }

    @Test
    @DisplayName("test Logic")
    fun testLogic() {
        val gasPrice = GasPrice().apply {
            result = aeee.gasPrice.core.vo.Result().apply {
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

        val blockInfoDTO = gasPriceService.manufactureGasPrice(gasPrice)

        assert(blockInfoDTO.number == BigDecimal(12617125))
        assert(blockInfoDTO.size == 6L)
        assert(blockInfoDTO.average == BigDecimal("2.3"))
        assert(blockInfoDTO.max == BigDecimal("3.0"))
        assert(blockInfoDTO.min == BigDecimal("1.0"))

        val transactionCounters = blockInfoDTO.transactionCounter
        assert(transactionCounters[0].gasPrice == BigDecimal("1.0"))
        assert(transactionCounters[0].count == 1L)

        assert(transactionCounters[1].gasPrice == BigDecimal("2.0"))
        assert(transactionCounters[1].count == 2L)

        assert(transactionCounters[2].gasPrice == BigDecimal("3.0"))
        assert(transactionCounters[2].count == 3L)
    }

    @Test
    @DisplayName("test Average Speed Less Than 1000ms")
    fun testAverageSpeedLessThan1000ms() {
        val testData: List<GasPrice> = Arrays.asList(
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
                gasPriceService.manufactureGasPrice(gasPrice)
            }
        }

        assert(sum / testData.size < 1000)
    }
}