package com.hfad.jokes.data

data class Jokes(
    val value: List<Value>
) {
    data class Value(
        val joke: String
    )
}