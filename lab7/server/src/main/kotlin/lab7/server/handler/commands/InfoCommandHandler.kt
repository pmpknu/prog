package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class InfoCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.InfoRequest::class) {
    override fun handle(request: Request): Response.InfoResponse {
        return Response.InfoResponse(
            info = collectionManager.info()
        )
    }

}