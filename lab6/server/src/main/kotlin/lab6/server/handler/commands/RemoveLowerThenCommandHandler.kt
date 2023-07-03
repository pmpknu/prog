package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class RemoveLowerThenCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.RemoveLowerRequest::class) {
    override fun handle(request: Request): Response.RemoveLowerResponse {
        return Response.RemoveLowerResponse(
            success = collectionManager.removeLowerThen((request as Request.RemoveLowerRequest).organization)
        )
    }

}