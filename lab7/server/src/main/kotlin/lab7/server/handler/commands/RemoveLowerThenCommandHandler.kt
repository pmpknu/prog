package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class RemoveLowerThenCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.RemoveLowerRequest::class) {
    override fun handle(request: Request): Response.RemoveLowerResponse {
        return Response.RemoveLowerResponse(
            success = collectionManager.removeLowerThen((request as Request.RemoveLowerRequest).organization, (request as Request.RemoveLowerRequest).user.id)
        )
    }

}