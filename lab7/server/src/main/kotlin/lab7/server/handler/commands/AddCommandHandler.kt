package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class AddCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.AddRequest::class) {
    override fun handle(request: Request): Response.AddResponse {
        return Response.AddResponse(
            success = collectionManager.add((request as Request.AddRequest).organization.copy(
                authorId = request.user.id
            ))
        )
    }

}