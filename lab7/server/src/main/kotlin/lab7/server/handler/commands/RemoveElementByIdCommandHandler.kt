package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class RemoveElementByIdCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.RemoveRequest::class) {
    override fun handle(request: Request): Response.RemoveResponse {
        return Response.RemoveResponse(
            success = collectionManager.removeElementByID((request as Request.RemoveRequest).id, (request as Request.RemoveRequest).user.id)
        )
    }

}