package lab5.console.utils

import lab5.console.utils.UserInputUtil.readOptionalField
import lab5.entities.organization.*
import java.io.BufferedReader
import java.io.IOException

/**
 * Утилитный класс для чтения пользовательского ввода.
 */
object UserInputUtil {
    private lateinit var reader: BufferedReader

    /**
     * Читает пользовательский ввод для создания объекта {@code Organization}.
     *
     * @return объект {@code Organization}, созданный из пользовательского ввода
     * @throws InputParseException если пользовательский ввод не может быть преобразован в {@code Organization}
     */
    fun readOrganizationInput(reader: BufferedReader): Organization {
        this.reader = reader

        while (true) {
            try {
                val name = "Введите имя организации: ".readStringField()
                val coordinates = "Введите координаты: ".readCoordinatesField()
                val annualTurnover = "Введите оборот (>0): ".readLongPositiveField()
                val type = "Введите тип организации (опционально, help - чтобы посмотртеть типы): ".readOptionalField(
                    ::parseOrganizationType,
                    ::helperOrganizationType
                )
                val postalAddress = "Введите почтовый адрес (опционально)\n\t\tВведите улицу: ".readOptionalField(
                    ::parseAddress
                )

                return Organization(
                    name = name,
                    coordinates = coordinates,
                    annualTurnover = annualTurnover,
                    type = type,
                    postalAddress = postalAddress
                )
            } catch (e: InputParseException) {
                println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
            }
        }
    }

    /**
     * Читает пользовательский ввод для создания объекта {@code Address}.
     *
     * @return объект {@code Organization}, созданный из пользовательского ввода
     * @throws InputParseException если пользовательский ввод не может быть преобразован в {@code Organization}
     */
    fun readAddressInput(reader: BufferedReader): Address {
        this.reader = reader

        while (true) {
            try {
                val postalAddress = "Введите почтовый адрес \n\t\tВведите улицу: ".readRequiredField(
                    ::parseAddress
                )
                return postalAddress!!
            } catch (e: InputParseException) {
                println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
            }
        }
    }

    private fun String.readStringField(): String {
        while (true) {
            try {
                print(this)
                val input = reader.readLine().trim()
                if (input.isNotBlank()) {
                    return input
                } else {
                    println("Поле обязательно к заполнению.")
                }
            } catch (e: Exception) {
                println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
            }
        }
    }

    private fun String.readCoordinatesField(): Coordinates {
        while (true) {
            println(this)
            var x: Float
            while (true) {
                try {
                    print("\t\tВведите X: ")
                    x = reader.readLine().trim().toFloat()
                    break
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
            var y: Double
            while (true) {
                try {
                    print("\t\tВведите Y (<=883): ")
                    y = reader.readLine().trim().toDouble()
                    if (y > 883) throw InputParseException("Слишком большое значение.")
                    break
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
            return Coordinates(x = x, y = y)
        }
    }

    private fun String.readLongPositiveField(): Long {
        while (true) {
            try {
                print(this)
                val field = reader.readLine().trim().toLong()
                return if (field > 0) field else throw InputParseException("Поле должно быть положительным.")
            } catch (e: Exception) {
                println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
            }
        }
    }

    private fun <T> String.readOptionalField(converter: (String) -> T, helper: (() -> Unit)? = null): T? {
        while (true) {
            print(this)
            var input = reader.readLine().trim()
            if (helper != null && input == "help") {
                helper()
                input = reader.readLine().trim()
            }

            try {
                return if (input.isNotBlank()) converter(input) else {
                    println("Опциональное поле не будет указано"); null
                }
            } catch (e: Exception) {
                println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
            }
        }
    }

    private fun <T> String.readRequiredField(converter: (String) -> T): T {
        while (true) {
            print(this)
            val input = reader.readLine().trim()

            if (input.isNotBlank()) {
                try {
                    return converter(input)
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
        }
    }

    private fun helperOrganizationType() {
        return OrganizationType.values().forEach { println(it) }
    }

    @Throws(InputParseException::class)
    private fun parseOrganizationType(type: String): OrganizationType? {
        return try {
            OrganizationType.valueOf(type.trim().uppercase())
        } catch (e: IllegalArgumentException) {
            throw InputParseException("Неверный формат организации: $this")
        }
    }

    /**
     * Преобразует строки в тип {@code Address}.
     *
     * @return экземпляр {@code Address}, соответствующий переданным параметрам
     * @throws InputParseException если параметры не могут быть преобразованы в {@code Address}
     * @throws IOException если произошла ошибка ввода-вывода при чтении параметров
     */
    @Throws(InputParseException::class)
    private fun parseAddress(input: String): Address? {
        try {
            var street: String = input
            if (street.isBlank()) {
                println("Поле не может быть пустым")
                while (true) {
                    try {
                        street = reader.readLine().trim()
                        if (street.isNotBlank())
                            break
                        else println("Поле не может быть пустым")
                    } catch (e: Exception) {
                        println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                    }
                }
            }
            var y: Long
            while(true) {
                try {
                    print("\t\tКоордината Y: ")
                    y = reader.readLine().trim().toLong()
                    break
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
            var x: Int
            while(true) {
                try {
                    print("\t\tКоордината X: ")
                    x = reader.readLine().trim().toInt()
                    break
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
            var name: String
            while(true) {
                try {
                    print("\t\tГород: ")
                    name = reader.readLine().trim()
                    if (name.isNotBlank())
                        break
                    else println("Поле не может быть пустым")
                } catch (e: Exception) {
                    println("Неверный формат, пожалуйста, попробуйте еще раз. Err: $e")
                }
            }
            return try {
                Address(street=street, Location(y=y, x=x, name=name))
            } catch (e: NumberFormatException) {
                throw InputParseException("Неверный формат координат")
            }
        } catch (e: IOException) {
            throw InputParseException("Ошибка чтения параметров")
        }
    }
}

class InputParseException(message: String) : RuntimeException(message)
