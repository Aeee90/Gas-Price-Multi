package aeee.gasPrice.api.service

import aeee.gasPrice.api.dto.BlockInfoDTO
import aeee.gasPrice.api.dto.TransactionCountDTO
import aeee.gasPrice.api.dto.comparator.TransactionCountDTOComparator
import aeee.gasPrice.core.api.InfuraAPI
import aeee.gasPrice.core.enums.NumberUnit
import aeee.gasPrice.core.util.UnitConvertor
import aeee.gasPrice.core.entity.GasPrice
import aeee.gasPrice.core.entity.Transaction
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.stream.Collectors

@Service
internal class GasPriceServiceDefault(
    private val infuraAPI: InfuraAPI
): GasPriceService {

    override fun manufactureGasPrice(): Mono<BlockInfoDTO> {
        return infuraAPI.getEth_getBlockByNumber().flatMap(this::manufactureGasPrice)
    }

    override fun manufactureGasPrice(gasPriceVO: GasPrice): Mono<BlockInfoDTO> {
        if(gasPriceVO == GasPrice.Empty) return Mono.just(BlockInfoDTO())

        val result = gasPriceVO.result
        val transactionEntities = result.transactions
        val blockInfoDTO = BlockInfoDTO()
        blockInfoDTO.average = BigDecimal.ZERO

        var sum: BigDecimal
        var max = BigDecimal.ZERO
        var min = BigDecimal.ZERO
        val size = transactionEntities.size
        if (transactionEntities.isNotEmpty()) {
            val counter: MutableMap<BigDecimal, Long> = HashMap()
            val first: BigDecimal = transactionEntities[0].gasPrice as BigDecimal
            max = first
            min = max
            sum = min
            counter[UnitConvertor.convertUnitWithRoundHalf(first, NumberUnit.WEI, NumberUnit.GIGA, 1)] = 1L
            for (i in 1 until size) {
                val tr: Transaction = transactionEntities[i]
                val gp: BigDecimal = tr.gasPrice as BigDecimal
                sum = sum.add(gp)
                if (gp.compareTo(max) > 0) max = gp
                if (gp.compareTo(min) < 0) min = gp
                val cgp: BigDecimal = UnitConvertor.convertUnitWithRoundHalf(gp, NumberUnit.WEI, NumberUnit.GIGA, 1)
                val count = counter[cgp]
                counter[cgp] = if (count == null) 1L else count + 1
            }

            blockInfoDTO.transactionCounter = counter.entries.stream()
                .map { (key, value) ->  TransactionCountDTO(key, value) }
                .sorted(TransactionCountDTOComparator.ComparatorAsc)
                .collect(Collectors.toList())

            val ave: BigDecimal = UnitConvertor.convertUnitWithRoundHalf(
                sum.divide(BigDecimal(size), 0, RoundingMode.HALF_UP),
                NumberUnit.WEI,
                NumberUnit.GIGA,
                1
            )
            blockInfoDTO.average = ave
        }
        blockInfoDTO.number = result.number as BigDecimal
        blockInfoDTO.size = size.toLong()
        blockInfoDTO.min = UnitConvertor.convertUnitWithRoundHalf(min, NumberUnit.WEI, NumberUnit.GIGA, 1)
        blockInfoDTO.max = UnitConvertor.convertUnitWithRoundHalf(max, NumberUnit.WEI, NumberUnit.GIGA, 1)

        return Mono.just(blockInfoDTO)
    }
}