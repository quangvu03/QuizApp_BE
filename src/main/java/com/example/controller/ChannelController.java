package com.example.controller;

import com.example.dtos.ChannelDTO;
import com.example.service.ChannelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller("api/channel")
public class ChannelController {

    @Inject
    private ChannelService channelService;

    @Post("/add")
    public HttpResponse<?> addChannel(@Body ChannelDTO channelDTO) {
        try {
            ChannelDTO addedChannel = channelService.addChannel(channelDTO);
            return HttpResponse.ok(Map.of("result", addedChannel));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", "Error adding channel: " + e.getMessage()));
        }
    }

    @Put("/update")
    public HttpResponse<?> updateChannel(@Body ChannelDTO channelDTO) {
        try {
            ChannelDTO updatedChannel = channelService.updateChannel(channelDTO);
            return HttpResponse.ok(Map.of("result", updatedChannel));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(Map.of("error", e.getMessage()));
        } catch (NoSuchElementException e) {
            return HttpResponse.notFound(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", "Error updating channel: " + e.getMessage()));
        }
    }

    @Get("/byUsername")
    public HttpResponse<?> findChannelsByUsername(@QueryValue String username) {
        try {
            return HttpResponse.ok(Map.of("result", channelService.findChannelsByUsername(username)));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", "Error finding channels: " + e.getMessage()));
        }
    }

    @Post(value = "/updateImage/{channelId}", consumes = MediaType.MULTIPART_FORM_DATA)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> updateChannelImage(
            @PathVariable Long channelId,
            @Part("image") CompletedFileUpload image) {
        try {
            ChannelDTO updatedChannel = channelService.updateChannelImage(channelId, image);
            Map<String, Object> response = new HashMap<>();
            response.put("result", updatedChannel);
            response.put("message", "Channel image updated successfully");
            return HttpResponse.ok(response);
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(Map.of("error", e.getMessage()));
        } catch (NoSuchElementException e) {
            return HttpResponse.notFound(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return HttpResponse.badRequest(Map.of("error", "Error processing image: " + e.getMessage()));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", "Error updating channel image: " + e.getMessage()));
        }
    }
}
