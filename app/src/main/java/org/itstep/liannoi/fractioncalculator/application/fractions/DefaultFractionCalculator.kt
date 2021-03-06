package org.itstep.liannoi.fractioncalculator.application.fractions

import org.itstep.liannoi.fractioncalculator.application.common.fractions.CalculationContext
import org.itstep.liannoi.fractioncalculator.application.common.fractions.FractionCalculator
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.AdditionQuery
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.DivisionQuery
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.MultiplicationQuery
import org.itstep.liannoi.fractioncalculator.application.fractions.queries.SubtractionQuery
import org.itstep.liannoi.fractioncalculator.infrastructure.math.DefaultMathClient

class DefaultFractionCalculator constructor(
    private val context: CalculationContext
) : FractionCalculator {

    private val mathClient: MathClient = DefaultMathClient()

    override fun addition(): Fraction =
        AdditionQuery.Handler(mathClient)
            .handle(AdditionQuery(context.getFirstFraction(), context.getSecondFraction()))

    override fun subtraction(): Fraction =
        SubtractionQuery.Handler(mathClient)
            .handle(SubtractionQuery(context.getFirstFraction(), context.getSecondFraction()))

    override fun multiplication(): Fraction =
        MultiplicationQuery.Handler()
            .handle(MultiplicationQuery(context.getFirstFraction(), context.getSecondFraction()))

    override fun division(): Fraction =
        DivisionQuery.Handler()
            .handle(DivisionQuery(context.getFirstFraction(), context.getSecondFraction()))
}
