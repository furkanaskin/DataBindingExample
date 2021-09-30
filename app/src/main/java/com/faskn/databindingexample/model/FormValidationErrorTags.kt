package com.faskn.databindingexample.model

enum class FormValidationErrorTags {
    INVALID_NAME,
    INVALID_SURNAME,
    INVALID_AGE,
    UNKNOWN;

    companion object {

        fun getType(name: String): FormValidationErrorTags {
            return values().find { it.name == name } ?: UNKNOWN
        }
    }
}