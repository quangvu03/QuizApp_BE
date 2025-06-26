package com.example.service.impl;

import com.example.configurations.ChannelMapper;
import com.example.dtos.ChannelDTO;
import com.example.entities.Account;
import com.example.entities.Channel;
import com.example.repositories.ChannelRepository;
import com.example.repositories.UserRepository;
import com.example.service.ChannelService;
import com.example.service.SaveImageService;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class ChannelServiceImpl implements ChannelService {

    @Inject
    private ChannelRepository channelRepository;

    @Inject
    private ChannelMapper channelMapper;

    @Inject
    private UserRepository userRepository;

    @Inject
    private SaveImageService saveImageService;

    @Override
    public ChannelDTO addChannel(ChannelDTO channelDTO) {
        if (channelDTO.getNameChanel() == null || channelDTO.getNameChanel().isEmpty()) {
            throw new IllegalArgumentException("Channel name cannot be empty");
        }
        Channel channel = channelMapper.toEntity(channelDTO);
        Channel savedChannel = channelRepository.save(channel);
        return channelMapper.toDTO(savedChannel);
    }

    @Override
    public ChannelDTO updateChannel(ChannelDTO channelDTO) {
        if (channelDTO.getId() == null) {
            throw new IllegalArgumentException("Channel ID cannot be null for update operation");
        }

        if (channelDTO.getNameChanel() == null || channelDTO.getNameChanel().isEmpty()) {
            throw new IllegalArgumentException("Channel name cannot be empty");
        }
        Channel existingChannel = channelRepository.findById(channelDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id: " + channelDTO.getId()));
        existingChannel.setNameChanel(channelDTO.getNameChanel());
        existingChannel.setDescription(channelDTO.getDescription());
        existingChannel.setBackground(channelDTO.getBackground());
        Channel updatedChannel = channelRepository.update(existingChannel);

        return channelMapper.toDTO(updatedChannel);
    }

    @Override
    public List<ChannelDTO> findChannelsByUsername(String username) {
        Optional<Account> userOptional = userRepository.findByUserName(username);
        if (userOptional.isEmpty()) {
            return new ArrayList<>();
        }
        Long userId = userOptional.get().getId();
        List<Channel> channels = channelRepository.findByUserId(userId);
        List<ChannelDTO> channelDTOs = new ArrayList<>();
        for (Channel channel : channels) {
            channelDTOs.add(channelMapper.toDTO(channel));
        }
        return channelDTOs;
    }

    @Override
    public ChannelDTO updateChannelImage(Long channelId, CompletedFileUpload image) throws IOException {
        // Validate input
        if (channelId == null) {
            throw new IllegalArgumentException("Channel ID cannot be null");
        }

        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        Optional<Channel> channelOptional = channelRepository.findById(channelId);
        if (channelOptional.isEmpty()) {
            throw new NoSuchElementException("Channel not found with id: " + channelId);
        }

        Channel channel = channelOptional.get();
        if (image.getSize() > 5 * 1024 * 1024) {
            throw new IOException("File too large, max 5MB");
        }
        String imageFileName = saveImageService.uploadImageQuiz(image);
        channel.setBackground(imageFileName);
        Channel updatedChannel = channelRepository.update(channel);
        return channelMapper.toDTO(updatedChannel);
    }
}
