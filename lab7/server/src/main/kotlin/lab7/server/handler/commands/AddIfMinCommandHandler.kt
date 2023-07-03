package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class AddIfMinCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.AddIfMinRequest::class) {
    override fun handle(request: Request): Response.AddIfMinResponse {
        return Response.AddIfMinResponse(
            success = collectionManager.addIfMin((request as Request.AddIfMinRequest).organization)
        )
    }

}