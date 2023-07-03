package lab5.command.commands

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExitExecutorTest {
    @Test fun `not empty args`() {
        var kek = false
        val commands = ExitExecutor { kek = true }
        commands.execute("adasd")
        assert(kek)
    }
    @Test fun `empty args`() {
        var kek = false
        val commands = ExitExecutor { kek = true }
        commands.execute("")
        assert(kek)
    }
}