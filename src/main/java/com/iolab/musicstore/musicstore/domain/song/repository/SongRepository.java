package com.iolab.musicstore.musicstore.domain.song.repository;

import com.iolab.musicstore.musicstore.domain.song.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = """
            WITH data_list AS (
                SELECT song_id FROM Song 
                ORDER BY song_id               
                OFFSET :#{#pageable.offset}
                LIMIT :#{#pageable.pageSize}
            )
            SELECT s.* FROM Song AS s 
            JOIN data_list AS dl 
            ON s.song_id = dl.song_id
            """,
            nativeQuery = true)
    Page<Song> getSong(Pageable pageable);
}
