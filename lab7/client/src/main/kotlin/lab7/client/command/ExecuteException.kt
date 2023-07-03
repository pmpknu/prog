package lab7.client.command

class ExecuteException(fieldName: String, description: String) : Exception("Bad $fieldName value: $description")