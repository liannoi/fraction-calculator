package org.itstep.liannoi.fractioncalculator.application.common.interfaces

interface MathClient {

    fun gcd(a: Int, b: Int): Int

    fun gcd(values: IntArray): Int

    fun lcm(a: Int, b: Int): Int

    fun lcm(values: IntArray): Int
}
