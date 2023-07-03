package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class ClearCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.ClearRequest::class) {
    override fun handle(request: Request): Response.ClearResponse {
        return Response.ClearResponse(
            collectionManager.clear(authorId = (request as Request.ClearRequest).user.id)
        )
    }

}