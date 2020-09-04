package org.itstep.liannoi.fractioncalculator.application.common.fractions

import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

interface CalculationContext {

    fun setStrategy(strategy: AssignStrategy)

    fun assign(value: Float)

    fun getFirstFraction(): Fraction

    fun getSecondFraction(): Fraction
}
