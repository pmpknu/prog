package lab6.client.handler

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import lab6.shared.entities.user.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class CommunicatorHandlerTest {
//    private val socket = mockk<DatagramSocket>()
//    @Test
//    fun testSendData() {
//        val handler = CommunicatorHandler(
//            port = 1234,
//            host = InetAddress.getLocalHost(),
//            user = User(UUID.randomUUID()),
//            datagramSocket = socket,
//        )
//
//        val data = byteArrayOf(1, 2, 3, 4, 5)
//        val slot = slot<DatagramPacket>()
//        every {
//            socket.send(capture(slot))
//        } returns Unit
//
//        handler.sendData(data)
//        assertTrue(slot.isCaptured)
//        assertEquals(data, slot.captured.data)
//    }
//}