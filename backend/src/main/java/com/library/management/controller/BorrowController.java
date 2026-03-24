package com.library.management.controller;

import com.library.management.dto.BorrowDTO;
import com.library.management.entity.Borrow;
import com.library.management.mapper.DTOMapper;
import com.library.management.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private DTOMapper dtoMapper;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long bookId, Principal principal) {
        try {
            Borrow borrow = borrowService.borrowBook(principal.getName(), bookId);
            return ResponseEntity.ok(dtoMapper.toBorrowDTO(borrow));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return/{borrowId}")
    public ResponseEntity<?> returnBook(@PathVariable Long borrowId, Principal principal) {
        try {
            Borrow borrow = borrowService.returnBook(borrowId, principal.getName());
            return ResponseEntity.ok(dtoMapper.toBorrowDTO(borrow));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/borrowed")
    public ResponseEntity<List<BorrowDTO>> getBorrowedBooks(Principal principal) {
        List<BorrowDTO> borrows = borrowService.getActiveBorrows(principal.getName())
                .stream()
                .map(dtoMapper::toBorrowDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(borrows);
    }
}
