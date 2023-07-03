package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class ClearCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.ClearRequest::class) {
    override fun handle(request: Request): Response.ClearResponse {
        return Response.ClearResponse(
            collectionManager.clear()
        )
    }

}