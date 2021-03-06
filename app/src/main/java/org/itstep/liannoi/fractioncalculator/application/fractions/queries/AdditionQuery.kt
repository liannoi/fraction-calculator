package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class AdditionQuery constructor(
    firstFraction: Fraction,
    secondFraction: Fraction
) : CommonDenominatorQuery(firstFraction, secondFraction) {

    class Handler constructor(
        mathClient: MathClient
    ) : CommonDenominatorQuery.Handler<AdditionQuery>(mathClient) {

        override fun calculateResult(firstFraction: Fraction, secondFraction: Fraction): Fraction {
            val result = Fraction()
            result.numerator = firstFraction.numerator + secondFraction.numerator
            result.denominator = secondFraction.denominator

            return result
        }
    }
}
