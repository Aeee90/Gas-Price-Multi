package aeee.gasPrice.core.entity

import aeee.gasPrice.core.util.UnitConvertor
import java.math.BigDecimal

class Transaction {

    constructor() {}

    constructor(gasPrice: BigDecimal) {
        this.gasPrice = gasPrice
    }

    private var _gasPrice: BigDecimal =  BigDecimal.ZERO
    var gasPrice: Any
        get() = _gasPrice
        set(value) {
            _gasPrice = handleNumber(value)
        }

    private fun <T> handleNumber(value: T) = when(value) {
        is BigDecimal -> value
        is String -> UnitConvertor.convertHexStrToDecimalBigDecimal(value)
        else -> BigDecimal.ZERO
    }
}