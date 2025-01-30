package com.rentvideo.service;

import com.rentvideo.dto.VideoDTO;
import com.rentvideo.entity.Video;
import com.rentvideo.exception.ResourceNotFoundException;
import com.rentvideo.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<VideoDTO> getAvailableVideos() {
        return videoRepository.findByAvailableTrue()
                .stream()
                .map(video -> {
                    VideoDTO dto = new VideoDTO();
                    dto.setId(video.getId());
                    dto.setTitle(video.getTitle());
                    dto.setDirector(video.getDirector());
                    dto.setGenre(video.getGenre());
                    dto.setAvailable(video.isAvailable());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public VideoDTO addVideo(VideoDTO dto) {
        Video video = new Video();
        video.setTitle(dto.getTitle());
        video.setDirector(dto.getDirector());
        video.setGenre(dto.getGenre());
        video.setAvailable(true);
        videoRepository.save(video);

        dto.setId(video.getId());
        dto.setAvailable(video.isAvailable());
        return dto;
    }

    public VideoDTO updateVideo(Long id, VideoDTO dto) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));

        video.setTitle(dto.getTitle());
        video.setDirector(dto.getDirector());
        video.setGenre(dto.getGenre());
        video.setAvailable(dto.isAvailable());

        videoRepository.save(video);
        return dto;
    }

    public void deleteVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));
        videoRepository.delete(video);
    }
}
