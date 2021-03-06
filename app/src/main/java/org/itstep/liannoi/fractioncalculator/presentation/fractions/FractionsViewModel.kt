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
    private lateinit var operationType: FractionsOperationType

    private val _denominatorMode: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val denominatorMode: LiveData<Boolean> = _denominatorMode

    val calc: MutableLiveData<String> = MutableLiveData<String>()

    val lastResult: MutableLiveData<String> = MutableLiveData<String>()

    private val currentValue: Int
        get() = calc.value!!.toInt()

    init {
        clear()
    }

    fun assignDenominator(@Suppress("UNUSED_PARAMETER") unused: View) {
        context.setStrategy(DenominatorAssignStrategy())
        assign(true)
    }

    fun calculate(@Suppress("UNUSED_PARAMETER") unused: View) {
        assignNumerator()
        val result: Fraction

        try {
            result = when (operationType) {
                FractionsOperationType.ADDITION -> calculator.addition()
                FractionsOperationType.SUBTRACTION -> calculator.subtraction()
                FractionsOperationType.MULTIPLICATION -> calculator.multiplication()
                FractionsOperationType.DIVISION -> calculator.division()
            }
        } catch (e: UninitializedPropertyAccessException) {
            Log.d(
                TAG,
                "calculate: An attempt to calculate the result, before choosing an operation."
            )

            e.printStackTrace()
            return
        }

        Log.d(TAG, "calculate: $result")
        lastResult.value = "${result.numerator}/${result.denominator}"
        context.reset()
    }

    fun prepareOperation(operationType: FractionsOperationType) {
        this.operationType = operationType
        assignNumerator()
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

    private fun assignNumerator(@Suppress("UNUSED_PARAMETER") unused: View? = null) {
        context.setStrategy(NumeratorAssignStrategy())
        assign()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Companion object
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        private const val TAG: String =
            "org.itstep.liannoi.fractioncalculator.presentation.fractions.FractionsViewModel"
    }
}
