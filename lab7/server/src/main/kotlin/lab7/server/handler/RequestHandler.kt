package lab7.server.handler

import lab7.shared.dto.Request
import lab7.shared.dto.Response

interface RequestHandler {
    fun handleRequest(request: Request, callback: (response: Response) -> Unit)
}