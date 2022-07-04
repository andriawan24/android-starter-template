package com.andriawan.common.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItem()
    fun reset()
}