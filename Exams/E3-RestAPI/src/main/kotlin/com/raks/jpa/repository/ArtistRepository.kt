package com.raks.jpa.repository;

import com.raks.jpa.entity.Artist
import org.springframework.data.jpa.repository.JpaRepository

interface ArtistRepository : JpaRepository<Artist, Int>