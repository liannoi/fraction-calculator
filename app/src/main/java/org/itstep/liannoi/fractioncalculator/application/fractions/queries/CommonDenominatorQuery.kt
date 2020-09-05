package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.BaseRequestHandler
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.Request
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

abstract class CommonDenominatorQuery constructor(
    private val _firstFraction: Fraction,
    private val _secondFraction: Fraction
) : Request<Fraction> {

    val firstFraction: Fraction
        get() = _firstFraction

    val secondFraction: Fraction
        get() = _secondFraction

    abstract class Handler<TQuery> constructor(
        private val mathClient: MathClient,
    ) : BaseRequestHandler<TQuery, Fraction> where TQuery : CommonDenominatorQuery {

        override fun handle(request: TQuery): Fraction {
            val lcm: Int = mathClient.lcm(
                request.firstFraction.denominator,
                request.secondFraction.denominator
            )

            return calculateResult(
                prepareFraction(lcm, request.firstFraction),
                prepareFraction(lcm, request.secondFraction)
            )
        }

        protected abstract fun calculateResult(
            firstFraction: Fraction,
            secondFraction: Fraction
        ): Fraction

        ///////////////////////////////////////////////////////////////////////////
        // Helpers
        ///////////////////////////////////////////////////////////////////////////

        private fun prepareFraction(lcm: Int, fraction: Fraction): Fraction {
            val result = Fraction()
            val tmp: Int = lcm / fraction.denominator
            result.numerator = tmp * fraction.numerator
            result.denominator = tmp * fraction.denominator

            return result
        }
    }
}
