package aeee.gasPrice.core.enums

import java.math.BigDecimal

enum class NumberUnit(
    val unit: BigDecimal
) {

    WEI(BigDecimal(1))
    , KILO(BigDecimal(1_000))
    , MEGA(BigDecimal(1_000_000))
    , GIGA(BigDecimal(1_000_000_000))
    , TERA(BigDecimal(1_000_000_000_000L))
    , PETA(BigDecimal(1_000_000_000_000_000L))
    , EXA(BigDecimal(1_000_000_000_000_000_000L));
}