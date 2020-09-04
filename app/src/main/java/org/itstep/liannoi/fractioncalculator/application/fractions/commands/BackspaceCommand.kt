package org.itstep.liannoi.fractioncalculator.application.fractions.commands

import androidx.lifecycle.MutableLiveData
import org.itstep.liannoi.fractioncalculator.application.common.interfaces.RequestHandler

class BackspaceCommand constructor(
    input: MutableLiveData<String>
) : AbstractCommand(input) {

    class Handler : RequestHandler<BackspaceCommand> {

        override fun handle(request: BackspaceCommand) {
            val value = request.input.value!!
            if (value.isEmpty()) {
                return
            }

            request.input.value = value.substring(0, value.length - 1)
        }
    }
}
