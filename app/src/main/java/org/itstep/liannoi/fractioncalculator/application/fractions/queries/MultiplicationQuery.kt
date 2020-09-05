package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.BaseRequestHandler
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.Request
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class MultiplicationQuery constructor(
    private val firstFraction: Fraction,
    private val secondFraction: Fraction
) : Request<Fraction> {

    class Handler : BaseRequestHandler<MultiplicationQuery, Fraction> {

        override fun handle(request: MultiplicationQuery): Fraction = Fraction(
            request.firstFraction.numerator * request.secondFraction.numerator,
            request.firstFraction.denominator * request.secondFraction.denominator
        )
    }
}
