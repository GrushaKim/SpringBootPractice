package com.example.petiteboard.repository;

import com.example.petiteboard.domain.Board;
import com.example.petiteboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String keyword);

    Board findByUser(User user);
}
