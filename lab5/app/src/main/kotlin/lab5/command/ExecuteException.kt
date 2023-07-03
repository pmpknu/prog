package lab5.command

class ExecuteException(fieldName: String, description: String) : Exception("Bad $fieldName value: $description")