package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response

interface CommandHandler {
    fun check(request: Request): Boolean
    fun handle(request: Request): Response
}