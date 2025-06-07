package com.iolab.musicstore.domain.artist.repository;

import com.iolab.musicstore.domain.artist.dto.AlbumByArtistInfoDto;
import com.iolab.musicstore.domain.artist.entity.ArtistAlbum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistAlbumRepository extends JpaRepository<ArtistAlbum, Long> {

    @Query(value = """
            WITH data_list AS (
                SELECT artist_id, 
                       album_id 
                FROM Artist_Album 
                ORDER BY artist_album_id               
                OFFSET :#{#pageable.offset}
                LIMIT :#{#pageable.pageSize}
            )
            SELECT al.album_id,
                   al.name,
                   al.cover_image,
                   al.release_date,
                   al.totalTracks
                   al.description
            FROM Album AS al
            JOIN data_list AS dl 
            ON al.album_id = dl.album_id
            """,
            nativeQuery = true)
    Page<AlbumByArtistInfoDto> findAlbumByArtistId(Long artistId, Pageable pageable);
}
