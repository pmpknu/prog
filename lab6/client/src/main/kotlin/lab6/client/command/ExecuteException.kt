package lab6.client.command

class ExecuteException(fieldName: String, description: String) : Exception("Bad $fieldName value: $description")