package com.example.petiteboard.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
//
//    public Board toEntity(){
//        Board board = Board.builder()
//                .id(id)
//                .writer(writer)
//                .title(title)
//                .content(content)
//                .build();
//        return board;
//    }


}
