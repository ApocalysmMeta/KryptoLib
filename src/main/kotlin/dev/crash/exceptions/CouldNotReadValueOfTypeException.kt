package dev.crash.exceptions

class CouldNotReadValueOfTypeException(type: String) : Exception("Could not read Value of type $type from packet!")