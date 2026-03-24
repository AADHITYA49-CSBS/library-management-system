package com.library.management.mapper;

import com.library.management.entity.*;
import com.library.management.dto.*;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public UserDTO toUserDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public User toUserEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }

    public BookDTO toBookDTO(Book book) {
        if (book == null) return null;
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setAvailableCopies(book.getAvailableCopies());
        return dto;
    }

    public Book toBookEntity(BookDTO dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setAvailableCopies(dto.getAvailableCopies());
        return book;
    }

    public BorrowDTO toBorrowDTO(Borrow borrow) {
        if (borrow == null) return null;
        BorrowDTO dto = new BorrowDTO();
        dto.setId(borrow.getId());
        dto.setUser(toUserDTO(borrow.getUser()));
        dto.setBook(toBookDTO(borrow.getBook()));
        dto.setBorrowDate(borrow.getBorrowDate());
        dto.setReturnDate(borrow.getReturnDate());
        dto.setPenaltyAmount(borrow.getPenaltyAmount());
        return dto;
    }
}
