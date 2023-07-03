package lab6.server.handler

import lab6.server.handler.commands.*
import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

class CommandsRequestHandler(
    private val collectionManager: CollectionManager<Organization>,
): RequestHandler {
    private val handlers: List<CommandHandler> = listOf(
        ShowCommandHandler(collectionManager),
        AddCommandHandler(collectionManager),
        InfoCommandHandler(collectionManager),
        ClearCommandHandler(collectionManager),
        RemoveElementByIdCommandHandler(collectionManager),
        ReturnMaxIdElCommandHandler(collectionManager),
        PrintDescendingCommandHandler(collectionManager),
        FilterGreaterThanPostalAddressCommandHandler(collectionManager),
        RemoveLowerThenCommandHandler(collectionManager),
        AddIfMinCommandHandler(collectionManager),
        AddIfMaxCommandHandler(collectionManager),
        UpdateByIdCommandHandler(collectionManager),
    )

    override fun handleRequest(request: Request): Response {
        return handlers.find { it.check(request) }?.handle(request) ?: Response.CommandNotFoundResponse()
    }
}