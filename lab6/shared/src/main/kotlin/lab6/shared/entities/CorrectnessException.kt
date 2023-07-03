package lab6.shared.entities

import java.lang.Exception

class CorrectnessException(field: String, value: String) :
    Exception("Некорректное значение поля $field: $value")