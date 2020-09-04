package org.itstep.liannoi.fractioncalculator.application.fractions.queries

import org.itstep.liannoi.fractioncalculator.application.common.commands.BaseRequestHandler
import org.itstep.liannoi.fractioncalculator.application.common.commands.Request
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

// TODO: 04.09.2020 Large refactoring of this class!
class AdditionQuery constructor(
    private val firstFraction: Fraction,
    private val secondFraction: Fraction
) : Request<Fraction> {

    class Handler : BaseRequestHandler<AdditionQuery, Fraction> {

        override fun handle(request: AdditionQuery): Fraction {
            val lcm = lcm(
                request.firstFraction.denominator.toLong(),
                request.secondFraction.denominator.toLong()
            )

            val tempFirstFraction = Fraction()
            val tempSecondFraction = Fraction()
            val result = Fraction()

            tempFirstFraction.numerator =
                (lcm / request.firstFraction.denominator) * request.firstFraction.numerator
            tempFirstFraction.denominator =
                (lcm / request.firstFraction.denominator) * request.firstFraction.denominator
            tempSecondFraction.numerator =
                (lcm / request.secondFraction.denominator) * request.secondFraction.numerator
            tempSecondFraction.denominator =
                (lcm / request.secondFraction.denominator) * request.secondFraction.denominator

            result.numerator = tempFirstFraction.numerator + tempSecondFraction.numerator
            result.denominator = tempSecondFraction.denominator

            return result
        }

        ///////////////////////////////////////////////////////////////////////////
        // Math
        ///////////////////////////////////////////////////////////////////////////

        private fun gcd(a: Long, b: Long): Long {
            var a = a
            var b = b
            while (b > 0) {
                val temp = b
                b = a % b // % is remainder
                a = temp
            }
            return a
        }

        private fun gcd(input: LongArray): Long {
            var result = input[0]
            for (i in 1 until input.size) result = gcd(result, input[i])
            return result
        }

        private fun lcm(a: Long, b: Long): Long {
            return a * (b / gcd(a, b))
        }

        private fun lcm(input: LongArray): Long {
            var result = input[0]
            for (i in 1 until input.size) result = lcm(result, input[i])
            return result
        }
    }
}
