package com.example.luis.remote.common

interface ApiService {

    fun <T> create(service: Class<T>): T
}