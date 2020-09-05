package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.BaseRequestHandler
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.Request
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class DivisionQuery constructor(
    private val firstFraction: Fraction,
    private val secondFraction: Fraction
) : Request<Fraction> {

    class Handler : BaseRequestHandler<DivisionQuery, Fraction> {

        override fun handle(request: DivisionQuery): Fraction = Fraction(
            request.firstFraction.numerator * request.secondFraction.denominator,
            request.firstFraction.denominator * request.secondFraction.numerator
        )
    }
}
