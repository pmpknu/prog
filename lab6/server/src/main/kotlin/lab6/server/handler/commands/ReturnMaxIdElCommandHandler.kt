package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class ReturnMaxIdElCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.MaxByIdRequest::class) {
    override fun handle(request: Request): Response.MaxByIdResponse {
        return Response.MaxByIdResponse(
            organization = collectionManager.returnMaxIdElement()
        )
    }

}