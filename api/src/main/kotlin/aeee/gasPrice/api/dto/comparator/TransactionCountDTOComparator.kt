package aeee.gasPrice.api.dto.comparator

import aeee.gasPrice.api.dto.TransactionCountDTO

object TransactionCountDTOComparator {

    val ComparatorAsc = Comparator<TransactionCountDTO> { o1: TransactionCountDTO, o2: TransactionCountDTO ->
            o1.gasPrice.compareTo(o2.gasPrice)
        }
}