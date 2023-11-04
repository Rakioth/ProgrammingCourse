package com.raks.jpa.mapper

interface JpaMapper<T, D> {

    fun toDomain(entity: T): D

    fun toEntity(dto: D):    T

}