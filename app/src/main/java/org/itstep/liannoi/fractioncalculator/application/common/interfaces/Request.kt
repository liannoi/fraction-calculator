package org.itstep.liannoi.fractioncalculator.application.common.interfaces

interface UnitRequest : Request<Unit>

interface Request<out TResponse> : BaseRequest

interface BaseRequest
