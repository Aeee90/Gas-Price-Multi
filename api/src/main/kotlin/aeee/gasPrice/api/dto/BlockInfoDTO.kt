package aeee.gasPrice.api.dto

import java.math.BigDecimal

class BlockInfoDTO {

    var number: BigDecimal? = null
    var size: Long? = null
    var average: BigDecimal? = null
    var max: BigDecimal? = null
    var min: BigDecimal? = null

    var transactionCounter: List<TransactionCountDTO> = emptyList()
}