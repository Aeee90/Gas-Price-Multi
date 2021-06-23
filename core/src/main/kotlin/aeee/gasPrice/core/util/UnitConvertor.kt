package aeee.gasPrice.core.util

import aeee.gasPrice.core.enums.NumberUnit
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.regex.Pattern

object UnitConvertor {

    private val HexReg = Pattern.compile("^0x([0-9a-f]+)")

    private fun getHexStr(hex: String): String {
        val matcher = HexReg.matcher(hex)
        return if (matcher.find()) matcher.group(1) else "0"
    }

    fun convertHexStrToDecimalBigDecimal(hex: String): BigDecimal = convertHexStrToDecimalLong(hex).toBigDecimal(MathContext.UNLIMITED)

    fun convertHexStrToDecimalLong(hex: String): Long = getHexStr(hex).toLong(16)

    fun convertUnit(value: BigDecimal, from: NumberUnit, to: NumberUnit): BigDecimal = value.multiply(from.unit).divide(to.unit)

    fun convertUnitWithRoundDown(value: BigDecimal, from: NumberUnit, to: NumberUnit, point: Int): BigDecimal = value.multiply(from.unit).divide(to.unit, point, RoundingMode.DOWN)

    fun convertUnitWithRoundUp(value: BigDecimal, from: NumberUnit, to: NumberUnit, point: Int): BigDecimal = value.multiply(from.unit).divide(to.unit, point, RoundingMode.UP)

    fun convertUnitWithRoundHalf(value: BigDecimal, from: NumberUnit, to: NumberUnit, point: Int): BigDecimal = value.multiply(from.unit).divide(to.unit, point, RoundingMode.HALF_UP)
}