package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class ReturnMaxIdElCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.MaxByIdRequest::class) {
    override fun handle(request: Request): Response.MaxByIdResponse {
        return Response.MaxByIdResponse(
            organization = collectionManager.returnMaxIdElement()
        )
    }

}