package lab6.server.handler

import lab6.shared.dto.Request
import lab6.shared.dto.Response

interface RequestHandler {
    fun handleRequest(request: Request): Response
}