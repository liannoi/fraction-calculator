package org.itstep.liannoi.fractioncalculator.presentation.fractions

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.itstep.liannoi.fractioncalculator.application.common.fractions.CalculationContext
import org.itstep.liannoi.fractioncalculator.application.common.fractions.FractionCalculator
import org.itstep.liannoi.fractioncalculator.application.fractions.DefaultCalculationContext
import org.itstep.liannoi.fractioncalculator.application.fractions.DefaultFractionCalculator
import org.itstep.liannoi.fractioncalculator.application.fractions.models.Fraction
import org.itstep.liannoi.fractioncalculator.application.fractions.strategy.DenominatorAssignStrategy
import org.itstep.liannoi.fractioncalculator.application.fractions.strategy.NumeratorAssignStrategy
import org.itstep.liannoi.fractioncalculator.presentation.PresentationDefaults

class FractionsViewModel : ViewModel() {

    private val context: CalculationContext = DefaultCalculationContext()
    private val calculator: FractionCalculator = DefaultFractionCalculator(context)
    private lateinit var operationType: FractionOperationType

    private val _denominatorMode: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val denominatorMode: LiveData<Boolean> = _denominatorMode

    val calc: MutableLiveData<String> = MutableLiveData<String>()

    val lastResult: MutableLiveData<String> = MutableLiveData<String>()

    private val currentValue: Int
        get() = calc.value!!.toInt()

    init {
        clear()
    }

    fun assignDenominator(unused: View) {
        context.setStrategy(DenominatorAssignStrategy())
        assign(true)
    }

    fun calculate(unused: View) {
        assignNumerator()
        val result: Fraction

        when (operationType) {
            FractionOperationType.ADDITION -> result = calculator.addition()
        }

        Log.d(TAG, "calculate: $result")
        lastResult.value = "${result.numerator}/${result.denominator}"
        context.reset()
    }

    fun prepareAddition(unused: View) {
        this.prepareOperation(FractionOperationType.ADDITION)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun clear() {
        calc.value = PresentationDefaults.CALC_FIELD_EMPTY
    }

    private fun assign(denominatorMode: Boolean = false) {
        context.assign(currentValue)
        clear()
        _denominatorMode.value = denominatorMode
    }

    private fun assignNumerator(unused: View? = null) {
        context.setStrategy(NumeratorAssignStrategy())
        assign()
    }

    private fun prepareOperation(operationType: FractionOperationType) {
        this.operationType = operationType
        assignNumerator()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Companion object
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        private const val TAG: String =
            "org.itstep.liannoi.fractioncalculator.presentation.fractions.FractionsViewModel"
    }
}
