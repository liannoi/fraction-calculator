package org.itstep.liannoi.fractioncalculator.application.common.fractions

import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction

interface FractionCalculator {

    fun calculate(): Fraction
}