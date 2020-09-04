package org.itstep.liannoi.fractioncalculator.application.common.fractions

import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

interface AssignStrategy {

    fun assign(first: Fraction, second: Fraction, value: Float)
}
