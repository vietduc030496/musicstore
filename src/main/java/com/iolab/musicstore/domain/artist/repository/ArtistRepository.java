package com.iolab.musicstore.domain.artist.repository;

import com.iolab.musicstore.domain.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
