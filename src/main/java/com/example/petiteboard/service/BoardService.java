package com.example.petiteboard.service;

import com.example.petiteboard.domain.Board;
import com.example.petiteboard.dto.BoardDto;
import com.example.petiteboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    private static final int PAGE_PER_BLOCK_CNT = 3;
    private static final int NUM_PER_PAGE = 5;

    private BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdTime(board.getCreatedTime())
                .updatedTime(board.getUpdatedTime())
                .build();
    }

    @Transactional
    public List<BoardDto> getBoardList(Integer nPage) {
        Page<Board> page = boardRepository.findAll(
                PageRequest.of(
                        nPage - 1, NUM_PER_PAGE, Sort.by(Sort.Direction.ASC, "createdTime"
                        )));

        List<Board> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(board));
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdTime(board.getCreatedTime())
                .updatedTime(board.getUpdatedTime())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boardEntities.isEmpty()) return boardDtoList;

        for(Board board : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCnt() {
        return boardRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[PAGE_PER_BLOCK_CNT];

        Double totalCnt = Double.valueOf(this.getBoardCnt());
        Integer lPageNum = (int)(Math.ceil((totalCnt/NUM_PER_PAGE)));
        Integer blockLPageNum = (lPageNum > curPageNum + PAGE_PER_BLOCK_CNT)
                ? curPageNum + PAGE_PER_BLOCK_CNT
                : lPageNum;

        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        for (int val = curPageNum, idx = 0; val <= blockLPageNum; val++, idx++) {
            pageList[idx] = val;
        }
        return pageList;
    }

}
