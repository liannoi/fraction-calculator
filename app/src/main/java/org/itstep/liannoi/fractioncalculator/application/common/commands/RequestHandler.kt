package org.itstep.liannoi.fractioncalculator.application.common.commands

interface BaseRequestHandler<in TRequest, TResponse>
        where TRequest : Request<TResponse> {

    fun handle(request: TRequest): TResponse
}

interface RequestHandler<in TRequest> : BaseRequestHandler<TRequest, Unit>
        where TRequest : Request<Unit>
