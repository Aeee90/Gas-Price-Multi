package aeee.gasPrice.core.vo

import aeee.gasPrice.core.util.UnitConvertor
import java.math.BigDecimal

class Result {

    companion object {
        val Empty = Result().apply {
            number = BigDecimal.valueOf(-2)
        }
    }

    private var _number: BigDecimal =  BigDecimal.ZERO
    var number: Any
        get() = _number
        set(value) {
            _number = handleNumber(value)
        }
    var transactions: List<Transaction> = ArrayList()

    private fun <T> handleNumber(value: T) = when(value) {
        is BigDecimal -> value
        is String -> UnitConvertor.convertHexStrToDecimalBigDecimal(value)
        else -> BigDecimal.ZERO
    }
}