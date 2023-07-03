package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class AddIfMaxCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.AddIfMaxRequest::class) {
    override fun handle(request: Request): Response.AddIfMaxResponse {
        return Response.AddIfMaxResponse(
            success = collectionManager.addIfMax((request as Request.AddIfMaxRequest).organization)
        )
    }

}