package aeee.gasPrice.core.util

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class UnitConvertorTest {

    @Test
    fun testToConvert16to10() {
        assert(
            UnitConvertor.convertHexStrToDecimalBigDecimal("0x104c533c00") == BigDecimal("70000000000")
        )
    }

}