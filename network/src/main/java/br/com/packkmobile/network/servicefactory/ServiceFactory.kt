package br.com.packkmobile.network.servicefactory

interface ServiceFactory {
    fun <T> create(service: Class<T>): T
}