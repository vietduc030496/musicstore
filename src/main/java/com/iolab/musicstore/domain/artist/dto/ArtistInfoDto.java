package com.iolab.musicstore.domain.artist.dto;

import com.iolab.musicstore.application.dto.response.PageInfo;
import com.iolab.musicstore.domain.artist.entity.Artist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class ArtistInfoDto {

    private Long artistId;
    private String name;
    private String artistType;
    private String avatar;
    private String description;

    private ArtistAlbumInfoDto albumInfo;

    public void setAlbumInfo(Page<AlbumByArtistInfoDto> albumPage) {
        PageInfo pageInfo = PageInfo.builder()
                .currentPage(albumPage.getPageable().getPageNumber() + 1)
                .total(albumPage.getTotalPages())
                .size(albumPage.getPageable().getPageSize())
                .sortBy("id")
                .sortOrder("asc")
                .build();

        albumInfo = new ArtistAlbumInfoDto();
        albumInfo.setAlbums(albumPage.getContent());
        albumInfo.setPageInfo(pageInfo);
    }

    public static ArtistInfoDto convert(Artist artist) {
        var dto = new ArtistInfoDto();
        dto.setArtistId(artist.getArtistId());
        dto.setName(artist.getName());
//        dto.setArtistType(); #TODO
        dto.setAvatar(artist.getAvatar());
        dto.setDescription(artist.getDescription());
        return dto;
    }

}
