package aeee.gasPrice.core.vo

import aeee.gasPrice.core.util.UnitConvertor
import java.math.BigDecimal

class Transaction {

    constructor() {}

    constructor(gasPrice: BigDecimal) {
        this.gasPrice = gasPrice
    }

    var gasPrice: Any = BigDecimal.ZERO
        set(value) {
            field = handleNumber(value)
        }

    private fun <T> handleNumber(value: T) = when(value) {
        is BigDecimal -> value
        is String -> UnitConvertor.convertHexStrToDecimalBigDecimal(value)
        else -> BigDecimal.ZERO
    }
}