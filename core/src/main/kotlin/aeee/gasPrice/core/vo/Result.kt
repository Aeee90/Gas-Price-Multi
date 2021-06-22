package aeee.gasPrice.core.vo

import java.math.BigDecimal

class Result {

    companion object {
        val Empty = Result().apply {
            number = BigDecimal.valueOf(-1)
        }
    }

    lateinit var number: BigDecimal
    val transactions: List<Transaction> = ArrayList()
}