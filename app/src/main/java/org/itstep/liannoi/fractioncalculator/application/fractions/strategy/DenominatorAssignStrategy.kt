package org.itstep.liannoi.fractioncalculator.application.fractions.strategy

import org.itstep.liannoi.fractioncalculator.application.common.fractions.AssignStrategy
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class DenominatorAssignStrategy : AssignStrategy {

    override fun assign(first: Fraction, second: Fraction, value: Int) {
        when {
            first.numerator != 0 ->
                second.numerator = value
            else ->
                first.numerator = value
        }
    }
}
