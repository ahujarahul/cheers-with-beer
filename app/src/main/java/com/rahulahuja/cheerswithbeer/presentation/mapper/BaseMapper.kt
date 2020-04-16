package com.rahulahuja.cheerswithbeer.presentation.mapper

interface BaseMapper<in A, out B> {
    fun map(data: A?): B?
}