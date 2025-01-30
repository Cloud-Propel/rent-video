package com.rentvideo.controller;

import com.rentvideo.dto.VideoDTO;
import com.rentvideo.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAvailableVideos() {
        return ResponseEntity.ok(videoService.getAvailableVideos());
    }

    @PostMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.addVideo(videoDTO));
    }

    @PutMapping("/manage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VideoDTO> updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.updateVideo(id, videoDTO));
    }

    @DeleteMapping("/manage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok("Video deleted successfully");
    }
}
