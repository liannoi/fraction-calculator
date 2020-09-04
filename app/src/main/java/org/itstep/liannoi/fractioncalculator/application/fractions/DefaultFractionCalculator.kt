package org.itstep.liannoi.fractioncalculator.application.fractions

import org.itstep.liannoi.fractioncalculator.application.common.fractions.CalculationContext
import org.itstep.liannoi.fractioncalculator.application.common.fractions.FractionCalculator
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.AdditionQuery

class DefaultFractionCalculator constructor(
    private val context: CalculationContext
) : FractionCalculator {

    override fun calculate(): Fraction =
        AdditionQuery.Handler()
            .handle(AdditionQuery(context.getFirstFraction(), context.getSecondFraction()))
}
