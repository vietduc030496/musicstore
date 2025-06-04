package com.iolab.musicstore.musicstore.domain.song.service;

import com.iolab.musicstore.musicstore.application.dto.response.CollectionDataResponse;
import com.iolab.musicstore.musicstore.application.dto.response.PageInfo;
import com.iolab.musicstore.musicstore.domain.song.dto.SongInfoDto;
import com.iolab.musicstore.musicstore.domain.song.entity.Song;
import com.iolab.musicstore.musicstore.domain.song.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public CollectionDataResponse<SongInfoDto> getSongs(int page,
                                                        int size ,
                                                        String sortParam) {
//        String[] split = sortParam.split(":");
//        Sort sort = Sort.by(split[0]);
//        if (split.length == 1 || "asc".equalsIgnoreCase(split[1])) {
//            sort.ascending();
//        } else if (("desc").equalsIgnoreCase(split[1])) {
//            sort.descending();
//        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<Song> pageData = songRepository.getSong(pageRequest);
        List<SongInfoDto> songInfoDtos = pageData.toList().stream().map(SongInfoDto::convert).toList();


        PageInfo pageInfo = PageInfo.builder()
                .currentPage(pageData.getPageable().getPageNumber() + 1)
                .total(pageData.getTotalPages())
                .size(pageData.getPageable().getPageSize())
                .sortBy("id")
                .sortOrder("asc")
                .build();


        return CollectionDataResponse.success(songInfoDtos, pageInfo);
    }
}
