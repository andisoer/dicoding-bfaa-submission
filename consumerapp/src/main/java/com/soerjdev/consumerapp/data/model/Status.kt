package com.soerjdev.consumerapp.data.model

class Status<T>(val status: Type, val data: T?, val message: String?){

    enum class Type {
        SUCCESS, FAILED
    }

    companion object {
        fun <T> success(data: T?): Status<T> {
            return Status(
                Type.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String?, data: T?): Status<T> {
            return Status(
                Type.FAILED,
                data,
                message
            )
        }
    }

}