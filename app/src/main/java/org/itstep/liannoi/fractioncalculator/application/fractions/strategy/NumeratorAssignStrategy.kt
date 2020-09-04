package org.itstep.liannoi.fractioncalculator.application.fractions.strategy

import org.itstep.liannoi.fractioncalculator.application.common.fractions.AssignStrategy
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

class NumeratorAssignStrategy : AssignStrategy {

    override fun assign(first: Fraction, second: Fraction, value: Float) {
        when {
            first.denominator != 0.0F ->
                second.denominator = value
            else ->
                first.denominator = value
        }
    }
}
