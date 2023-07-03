package lab7.server.handler.commands

import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager

class FilterGreaterThanPostalAddressCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.FilterGreaterRequest::class) {
    override fun handle(request: Request): Response.FilterGreaterResponse {
        return Response.FilterGreaterResponse(
            coll = collectionManager.filterGreaterThanPostalAddress((request as Request.FilterGreaterRequest).address).toList()
        )
    }

}