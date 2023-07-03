package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class UpdateByIdCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.UpdateRequest::class) {
    override fun handle(request: Request): Response.UpdateResponse {
        return Response.UpdateResponse(
            success = collectionManager.updateElementByID((request as Request.UpdateRequest).organization)
        )
    }

}