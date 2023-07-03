package lab6.server.handler.commands

import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class FilterGreaterThanPostalAddressCommandHandler(
    private val collectionManager: CollectionManager<Organization>
): AbstractCommandHandler(Request.FilterGreaterRequest::class) {
    override fun handle(request: Request): Response.FilterGreaterResponse {
        return Response.FilterGreaterResponse(
            coll = collectionManager.filterGreaterThanPostalAddress((request as Request.FilterGreaterRequest).address).toList()
        )
    }

}