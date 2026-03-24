package com.library.management.controller;

import com.library.management.dto.BookDTO;
import com.library.management.entity.Book;
import com.library.management.mapper.DTOMapper;
import com.library.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DTOMapper dtoMapper;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(dtoMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = dtoMapper.toBookEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(dtoMapper.toBookDTO(savedBook));
    }
}
