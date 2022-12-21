package com.andriawan.common.paginator

interface Pagination<Key, Item> {
    suspend fun loadNextItem()
    fun reset()
}