package com.example.service;

import com.example.dtos.ChannelDTO;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.IOException;
import java.util.List;

public interface ChannelService {

    ChannelDTO addChannel(ChannelDTO channelDTO);

    ChannelDTO updateChannel(ChannelDTO channelDTO);

    List<ChannelDTO> findChannelsByUsername(String username);

    ChannelDTO updateChannelImage(Long channelId, CompletedFileUpload image) throws IOException;
}
