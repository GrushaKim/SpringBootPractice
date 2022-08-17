package com.example.petiteboard.dto;


import com.example.petiteboard.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(
            Long id,
            String writer,
            String title,
            String content,
            LocalDateTime createdTime,
            LocalDateTime updatedTime
    ) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
