package org.itstep.liannoi.fractioncalculator.application.fractions

import org.itstep.liannoi.fractioncalculator.application.common.fractions.CalculationContext
import org.itstep.liannoi.fractioncalculator.application.common.fractions.FractionCalculator
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.AdditionQuery
import org.itstep.liannoi.fractioncalculator.infrastructure.math.DefaultMathClient

class DefaultFractionCalculator constructor(
    private val context: CalculationContext
) : FractionCalculator {

    private val mathClient: MathClient = DefaultMathClient()

    override fun addition(): Fraction =
        AdditionQuery.Handler(mathClient)
            .handle(AdditionQuery(context.getFirstFraction(), context.getSecondFraction()))
}
