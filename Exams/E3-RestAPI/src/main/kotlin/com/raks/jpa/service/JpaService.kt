package com.raks.jpa.service

import com.raks.jpa.mapper.JpaMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository

abstract class JpaService<T : Any, D : Any, ID : Any>(
    private val repository: JpaRepository<T, ID>,
    private val mapper:     JpaMapper<T, D>,
) {

    fun create(dto: D): D =
        mapper.toDomain(
            repository.save(mapper.toEntity(dto))
        )

    fun read(id: ID): D =
        mapper.toDomain(
            repository.findById(id)
                      .orElseThrow { EntityNotFoundException() }
        )

    fun update(dto: D) {
        repository.save(mapper.toEntity(dto))
    }

    fun delete(id: ID) =
        repository.deleteById(id)

    fun all(): List<D> =
        repository.findAll().map { mapper.toDomain(it) }

}