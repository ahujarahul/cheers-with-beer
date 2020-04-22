package com.rahulahuja.cheerswithbeer.utils

interface BaseMapper<in A, out B> {
    fun map(data: A?): B?
}