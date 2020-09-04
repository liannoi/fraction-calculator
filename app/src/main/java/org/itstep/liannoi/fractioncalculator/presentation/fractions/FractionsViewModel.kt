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
import org.itstep.liannoi.fractioncalculator.application.fractions.strategy.DenominatorAssignStrategy
import org.itstep.liannoi.fractioncalculator.application.fractions.strategy.NumeratorAssignStrategy
import org.itstep.liannoi.fractioncalculator.presentation.PresentationDefaults

class FractionsViewModel : ViewModel() {

    private val calculationContext: CalculationContext = DefaultCalculationContext()
    private val calculator: FractionCalculator = DefaultFractionCalculator(calculationContext)

    private val _denominatorMode: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val denominatorMode: LiveData<Boolean> = _denominatorMode

    val calc: MutableLiveData<String> = MutableLiveData<String>()

    private val currentValue: Int
        get() = calc.value!!.toInt()

    init {
        clear()
    }

    fun assignDenominator(unused: View) {
        calculationContext.setStrategy(DenominatorAssignStrategy())
        assign(true)
    }

    fun assignNumerator(unused: View? = null) {
        calculationContext.setStrategy(NumeratorAssignStrategy())
        assign()
    }

    fun calculate(unused: View) {
        assignNumerator()
        Log.d(TAG, "calculate: ${calculator.calculate()}")
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun clear() {
        calc.value = PresentationDefaults.CALC_FIELD_EMPTY
    }

    private fun assign(denominatorMode: Boolean = false) {
        calculationContext.assign(currentValue)
        clear()
        _denominatorMode.value = denominatorMode
    }

    ///////////////////////////////////////////////////////////////////////////
    // Companion object
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        private const val TAG: String =
            "org.itstep.liannoi.fractioncalculator.presentation.fractions.FractionsViewModel"
    }
}
