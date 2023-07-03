package lab7.server.handler.commands

import lab7.server.collection.databasehandler.dbuser.UserManager
import lab7.shared.dto.Request
import lab7.shared.dto.Response

class AuthorizeCommandHandler(private val manager: UserManager): AbstractCommandHandler(Request.AuthorizeRequest::class) {
    override fun handle(request: Request): Response.AuthorizeResponse {
        val ar = request as Request.AuthorizeRequest
        val user = manager.checkUser(ar.user)
        return Response.AuthorizeResponse(user)
    }
}