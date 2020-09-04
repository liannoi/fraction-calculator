package org.itstep.liannoi.fractioncalculator.application.common.commands

interface UnitRequest : Request<Unit>

interface Request<out TResponse> : BaseRequest

interface BaseRequest
