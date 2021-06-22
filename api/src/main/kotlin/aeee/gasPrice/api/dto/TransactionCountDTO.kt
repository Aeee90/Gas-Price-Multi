package aeee.gasPrice.api.dto

import java.math.BigDecimal

class TransactionCountDTO(
    val gasPrice: BigDecimal
    , val count: Long
) {
}