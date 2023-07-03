package lab6.server.communicator

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Nested
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import kotlin.test.*

class DatagramChannelCommunicatorTest {
    private val channel = mockk<DatagramChannel>()
    init {
        every { channel.isBlocking } returns false
    }
    private val chunkSize = 10;
    private val communicator = DatagramChannelCommunicator(channel, chunkSize)

    @Nested
    inner class SendData {
        val address = InetSocketAddress(1234)

        @Test
        fun `send exactly one chunk`() {
            val data = (1..chunkSize)
                .map { it.toByte() }
                .toByteArray()

            val bufferSlot = slot<ByteBuffer>()
            every {
                channel.send(capture(bufferSlot), any())
            } returns chunkSize

            communicator.sendData(data, address)

            verify(exactly = 1) {
                channel.send(any(), address)
            }

            assertTrue(bufferSlot.isCaptured)
            val capturedByteArray = ByteArray(bufferSlot.captured.capacity())
            bufferSlot.captured.get(capturedByteArray)
            assertContentEquals(data, capturedByteArray.dropLast(1).toByteArray())
        }

        @Test
        fun `send less than one chunk`() {
            assert(chunkSize - 2 >= 1) {"wtf you need to test empty array??????"}
            val data = (1..chunkSize - 2)
                .map { it.toByte() }
                .toByteArray()


            val bufferSlot = slot<ByteBuffer>()
            every {
                channel.send(capture(bufferSlot), any())
            } returns chunkSize

            communicator.sendData(data, address)

            verify(exactly = 1) {
                channel.send(any(), address)
            }

            assertTrue(bufferSlot.isCaptured)
            val capturedByteArray = ByteArray(bufferSlot.captured.capacity())
            bufferSlot.captured.get(capturedByteArray)
            assertContentEquals(data, capturedByteArray.dropLast(1).toByteArray())
        }

        @Test
        fun `send 2 fucking chunks`() {
            val data = (1..chunkSize * 2)
                .map { it.toByte() }
                .toByteArray()
            val bufferSlot = slot<ByteBuffer>()

            val totalChunks = (data.size).ceilDiv(chunkSize)

            val totalData = MutableList<Byte>(0, { 0.toByte() })
            every {
                channel.send(capture(bufferSlot), any())
            } answers {
                val received = ByteArray(bufferSlot.captured.capacity())
                bufferSlot.captured.get(received)
                totalData.addAll(received.toList().dropLast(1))
                chunkSize
            }

            communicator.sendData(data, address)

            verify(exactly = totalChunks) { channel.send(any(), address) }

            assertContentEquals(data, totalData.toByteArray())
        }
    }
}
