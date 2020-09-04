package org.itstep.liannoi.fractioncalculator.application.fractions.commands

import androidx.lifecycle.MutableLiveData
import org.itstep.liannoi.fractioncalculator.application.common.commands.RequestHandler

class AppendCommand constructor(
    input: MutableLiveData<String>,
    private val value: String
) : AbstractCommand(input) {

    class Handler : RequestHandler<AppendCommand> {

        override fun handle(request: AppendCommand) {
            if (request.isFieldEmpty) {
                request.input.value = request.value
                return
            }

            request.input.value += request.value
        }
    }
}
