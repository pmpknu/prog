package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class AddCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.AddRequest::class) {
    override fun handle(request: Request): Response.AddResponse {
        return Response.AddResponse(
            success = collectionManager.add((request as Request.AddRequest).organization)
        )
    }

}