package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class UpdateByIdCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.UpdateRequest::class) {
    override fun handle(request: Request): Response.UpdateResponse {
        return Response.UpdateResponse(
            success = collectionManager.updateElementByID((request as Request.UpdateRequest).organization, (request as Request.UpdateRequest).user.id)
        )
    }

}