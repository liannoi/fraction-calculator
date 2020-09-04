package org.itstep.liannoi.fractioncalculator.infrastructure.math

import org.itstep.liannoi.fractioncalculator.application.common.interfaces.MathClient

class DefaultMathClient : MathClient {

    override fun gcd(a: Int, b: Int): Int {
        var firstValue: Int = a
        var secondValue: Int = b

        while (secondValue > 0) {
            val tmp: Int = secondValue
            secondValue = firstValue % secondValue
            firstValue = tmp
        }

        return firstValue
    }

    override fun gcd(values: IntArray): Int {
        var result: Int = values[0]
        (1 until values.size).forEach { result = gcd(result, values[it]) }

        return result
    }

    override fun lcm(a: Int, b: Int): Int = a * (b / gcd(a, b))

    override fun lcm(values: IntArray): Int {
        var result: Int = values[0]
        (1 until values.size).forEach { result = lcm(result, values[it]) }

        return result
    }
}
