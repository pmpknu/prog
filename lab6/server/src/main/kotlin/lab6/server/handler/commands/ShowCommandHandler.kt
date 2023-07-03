package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class ShowCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.ShowRequest::class) {
    override fun handle(request: Request): Response.ShowResponse {
        return Response.ShowResponse(
            coll = collectionManager.show().toList()
        )
    }

}