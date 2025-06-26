package com.example.configurations;

import com.example.dtos.ChannelDTO;
import com.example.entities.Channel;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;

import java.util.List;

@Singleton
@Mapper(componentModel = "jsr330")
public interface ChannelMapper {
    
    ChannelDTO toDTO(Channel channel);
    
    Channel toEntity(ChannelDTO channelDTO);
    
    List<ChannelDTO> toDTOList(List<Channel> channels);
    
    List<Channel> toEntityList(List<ChannelDTO> channelDTOs);
}