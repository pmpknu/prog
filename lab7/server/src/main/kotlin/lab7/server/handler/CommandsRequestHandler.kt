package lab7.server.handler

import lab7.server.collection.databasehandler.dbuser.UserManager
import lab7.server.handler.commands.*
import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager
import kotlin.concurrent.thread

class CommandsRequestHandler(
    private val collectionManager: CollectionManager<Organization>,
    private val userManager: UserManager,
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
        AuthorizeCommandHandler(userManager)
    )

    override fun handleRequest(request: Request, callback: (response: Response) -> Unit) {
        thread {
            val response = try {
                handlers.find { it.check(request) }?.handle(request) ?: Response.CommandNotFoundResponse()
            } catch (e: Exception) {
                Response.ExceptionResponse(e.toString())
            }
            callback(response)
        }
    }
}