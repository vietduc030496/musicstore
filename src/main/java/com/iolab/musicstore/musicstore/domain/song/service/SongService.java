package com.iolab.musicstore.musicstore.domain.song.service;

import com.iolab.musicstore.musicstore.application.dto.response.CollectionDataResponse;
import com.iolab.musicstore.musicstore.application.dto.response.PageInfo;
import com.iolab.musicstore.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.musicstore.domain.song.dto.SongInfoDto;
import com.iolab.musicstore.musicstore.domain.song.entity.Song;
import com.iolab.musicstore.musicstore.domain.song.repository.SongRepository;
import com.iolab.musicstore.musicstore.domain.upload.dto.FileAttachInfoDto;
import com.iolab.musicstore.musicstore.domain.upload.entity.FileAttach;
import com.iolab.musicstore.musicstore.domain.upload.repository.FileAttachRepository;
import com.iolab.musicstore.musicstore.infrastructure.util.FileAttachUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final FileAttachRepository fileAttachRepository;

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

        List<SongInfoDto> songInfoDtos = pageData.toList()
                                                    .stream()
                                                    .map(SongInfoDto::convert)
                                                    .toList();

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(pageData.getPageable().getPageNumber() + 1)
                .total(pageData.getTotalPages())
                .size(pageData.getPageable().getPageSize())
                .sortBy("id")
                .sortOrder("asc")
                .build();

        return CollectionDataResponse.success(songInfoDtos, pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public SingleDataResponse<FileAttachInfoDto> uploadAudio(MultipartFile audio) throws IOException {
        String fileName = FileAttachUtil.saveUploadFile(audio);

        FileAttach fileAttach = new FileAttach();
        fileAttach.setExtension(FilenameUtils.getExtension(fileName));
        fileAttach.setData(fileName);
        fileAttach = fileAttachRepository.save(fileAttach);

        FileAttachInfoDto dto = FileAttachInfoDto.convert(fileAttach);

        return SingleDataResponse.success(dto);
    }
}
