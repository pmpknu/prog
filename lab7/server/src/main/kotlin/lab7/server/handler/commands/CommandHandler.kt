package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response

interface CommandHandler {
    fun check(request: Request): Boolean
    fun handle(request: Request): Response
}