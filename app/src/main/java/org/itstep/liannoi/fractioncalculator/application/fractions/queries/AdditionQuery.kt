package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.BaseRequestHandler
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.Request
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class AdditionQuery constructor(
    private val firstFraction: Fraction,
    private val secondFraction: Fraction
) : Request<Fraction> {

    class Handler constructor(
        private val mathClient: MathClient
    ) : BaseRequestHandler<AdditionQuery, Fraction> {

        override fun handle(request: AdditionQuery): Fraction {
            val lcm: Int = calculateLcm(request)

            return calculateResult(
                prepareFirstFraction(lcm, request),
                prepareSecondFraction(lcm, request)
            )
        }

        ///////////////////////////////////////////////////////////////////////////
        // Helpers
        ///////////////////////////////////////////////////////////////////////////

        private fun calculateLcm(request: AdditionQuery): Int = mathClient.lcm(
            request.firstFraction.denominator,
            request.secondFraction.denominator
        )

        private fun prepareFirstFraction(lcm: Int, request: AdditionQuery): Fraction {
            val result = Fraction()

            result.numerator =
                (lcm / request.firstFraction.denominator * request.firstFraction.numerator)

            result.denominator =
                (lcm / request.firstFraction.denominator) * request.firstFraction.denominator

            return result
        }

        private fun prepareSecondFraction(lcm: Int, request: AdditionQuery): Fraction {
            val result = Fraction()

            result.numerator =
                (lcm / request.secondFraction.denominator) * request.secondFraction.numerator

            result.denominator =
                (lcm / request.secondFraction.denominator) * request.secondFraction.denominator

            return result
        }

        private fun calculateResult(firstFraction: Fraction, secondFraction: Fraction): Fraction {
            val result = Fraction()
            result.numerator = firstFraction.numerator + secondFraction.numerator
            result.denominator = secondFraction.denominator

            return result
        }
    }
}
