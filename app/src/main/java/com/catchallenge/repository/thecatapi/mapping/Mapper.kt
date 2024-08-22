package com.catchallenge.repository.thecatapi.mapping

fun interface Mapper<in From, out To> {
    fun map(from: From): To
}

fun <F, T> Mapper<F, T>.mapAll(list: List<F>) = list.map { map(it) }