package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class AddIfMinCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.AddIfMinRequest::class) {
    override fun handle(request: Request): Response.AddIfMinResponse {
        return Response.AddIfMinResponse(
            success = collectionManager.addIfMin((request as Request.AddIfMinRequest).organization)
        )
    }

}