package com.raks.jpa.api

import com.raks.jpa.dto.JpaDto
import com.raks.jpa.service.JpaService
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class JpaController<T : Any, D : JpaDto<ID>, ID : Any>(
    private val service: JpaService<T, D, ID>
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<D>> =
        ResponseEntity.ok(service.all())

    @GetMapping("/{id}")
    fun get(@PathVariable id: ID): ResponseEntity<D> =
        try {
            ResponseEntity.ok(service.read(id))
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }

    @PostMapping
    fun post(@RequestBody dto: D): ResponseEntity<D> =
        ResponseEntity(service.create(dto), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun put(@RequestBody dto: D, @PathVariable id: ID): ResponseEntity<Unit> =
        try {
            ResponseEntity(
                service.update(merge(dto, service.read(id))),
                HttpStatus.NO_CONTENT
            )
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        } catch (e: NoSuchFieldException) {
            ResponseEntity.badRequest().build()
        }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<Unit> =
        ResponseEntity(service.delete(id), HttpStatus.NO_CONTENT)

    private fun merge(dto: D, readDto: D): D {
        for (property in dto.javaClass.declaredFields) {
            property.isAccessible = true
            val value = property.get(dto)

            if (value != null) {
                val readDtoProperty = readDto.javaClass.getDeclaredField(property.name)
                readDtoProperty.isAccessible = true
                readDtoProperty.set(readDto, value)
            }
        }

        return readDto
    }

}