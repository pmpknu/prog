package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class PrintDescendingCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.PrintDescendingRequest::class) {
    override fun handle(request: Request): Response.PrintDescendingResponse {
        return Response.PrintDescendingResponse(
            coll = collectionManager.returnDescending().toList()
        )
    }

}