package com.rentvideo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDTO {
    
    private Long id;
    private String title;
    private String director;
    private String genre;
    private boolean available;
}
