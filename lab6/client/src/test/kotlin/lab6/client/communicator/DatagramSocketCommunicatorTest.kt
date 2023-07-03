package lab6.client.communicator

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress
import kotlin.test.assertContentEquals
import kotlin.test.expect

class DatagramSocketCommunicatorTest {
    val address = InetSocketAddress(1234)
    val socket = mockk<DatagramSocket>()
    val chunkSize = 20
    val communicator = DatagramSocketCommunicator(socket, chunkSize)

    @Nested
    inner class ReceiveData {
        @Test
        fun `receive less than one chunk`() {
            assert(chunkSize - 10 >= 1) { "wtf receive empty array???" }
            val data = (1..chunkSize - 10).map { it.toByte() }.toByteArray()

            val datagramPacketSlot = slot<DatagramPacket>()
            every {
                socket.receive(capture(datagramPacketSlot))
            } answers {
                datagramPacketSlot.captured.data = data + 0
                datagramPacketSlot.captured.socketAddress = address
            }

            val actual = communicator.receiveData()
            verify(exactly = 1) {
                socket.receive(any())
            }

            assertEquals(address, actual.second)
            assertContentEquals(data, actual.first)
        }

        @Test
        fun `receive two chunks`() {
            val chunksNumber = 100
            val data = (1..chunksNumber * chunkSize).map { it.toByte() }.toByteArray()

            val datagramPacketSlot = slot<DatagramPacket>()
            var callNumber = 0
            every {
                socket.receive(capture(datagramPacketSlot))
            } answers {
                if (callNumber == chunksNumber - 1) {
                    datagramPacketSlot.captured.data =
                        data.sliceArray(callNumber * chunkSize until (callNumber + 1) * chunkSize) + 0
                } else {
                    datagramPacketSlot.captured.data =
                        data.sliceArray(callNumber * chunkSize until (callNumber + 1) * chunkSize) + 1
                }
                datagramPacketSlot.captured.socketAddress = address
                callNumber++;
            }

            val actual = communicator.receiveData()
            verify(exactly = chunksNumber) {
                socket.receive(any())
            }

            assertEquals(address, actual.second)
            assertContentEquals(data, actual.first)
        }
    }
}