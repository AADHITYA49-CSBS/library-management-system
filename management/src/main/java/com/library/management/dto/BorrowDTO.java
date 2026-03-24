package com.library.management.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BorrowDTO {
    private Long id;
    private UserDTO user;
    private BookDTO book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Double penaltyAmount;
}
