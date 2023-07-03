package lab7.server.handler.commands

import com.sun.org.apache.xpath.internal.operations.Bool
import lab7.server.handler.RequestHandler
import lab7.shared.dto.Request
import kotlin.reflect.KClass

abstract class AbstractCommandHandler(
    protected val requestClass: KClass<out Request>
) : CommandHandler {
     override fun check(request: Request): Boolean {
        return requestClass == request::class
    }
}