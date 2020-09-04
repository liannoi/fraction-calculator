package org.itstep.liannoi.fractioncalculator.application.fractions.commands

import androidx.lifecycle.MutableLiveData
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.UnitRequest
import org.itstep.liannoi.fractioncalculator.presentation.PresentationDefaults

abstract class AbstractCommand constructor(
    var input: MutableLiveData<String>
) : UnitRequest {

    protected val isFieldEmpty: Boolean
        get() = input.value == PresentationDefaults.CALC_FIELD_EMPTY

    protected fun isLastEquals(symbol: Char): Boolean {
        val value: String = input.value!!

        return value[value.length - 1] == symbol
    }
}
