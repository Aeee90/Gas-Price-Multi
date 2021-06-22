package aeee.gasPrice.core.vo

import java.math.BigDecimal

class Result {

    private lateinit var number: BigDecimal
    private val transactions: List<Transaction> = ArrayList<Transaction>()
}