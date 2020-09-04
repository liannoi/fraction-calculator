package org.itstep.liannoi.fractioncalculator.application.fractions

import org.itstep.liannoi.fractioncalculator.application.common.fractions.AssignStrategy
import org.itstep.liannoi.fractioncalculator.application.common.fractions.CalculationContext
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class DefaultCalculationContext : CalculationContext {

    private lateinit var strategy: AssignStrategy
    private val firstFraction: Fraction = Fraction()
    private val secondFraction: Fraction = Fraction()

    override fun setStrategy(strategy: AssignStrategy) {
        this.strategy = strategy
    }

    override fun getFirstFraction(): Fraction = firstFraction

    override fun getSecondFraction(): Fraction = secondFraction

    override fun assign(value: Int) {
        strategy.assign(firstFraction, secondFraction, value)
    }
}
