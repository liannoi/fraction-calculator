package org.itstep.liannoi.fractioncalculator.application.fractions.commands

import androidx.lifecycle.MutableLiveData
import org.itstep.liannoi.fractioncalculator.application.common.commands.RequestHandler
import org.itstep.liannoi.fractioncalculator.presentation.PresentationDefaults

class DivideCommand constructor(
    input: MutableLiveData<String>
) : AbstractCommand(input) {

    class Handler : RequestHandler<DivideCommand> {

        override fun handle(request: DivideCommand) {
            val symbol: Char = PresentationDefaults.CALC_SYMBOL_DIVIDE
            if (request.isFieldEmpty || request.isLastEquals(symbol)) return

            request.input.value += symbol
        }
    }
}
