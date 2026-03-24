package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.entity.Borrow;
import com.library.management.entity.User;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowRepository;
import com.library.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Assuming a simple 14-day limit and a fixed penalty amount per day late
    private static final int MAX_BORROW_DAYS = 14;
    private static final double PENALTY_PER_DAY = 5.0; // Assume currency is mapped in UI

    @Transactional
    public Borrow borrowBook(String userEmail, Long bookId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is currently not available.");
        }

        int activeBorrows = borrowRepository.countByUserAndReturnDateIsNull(user);
        if (activeBorrows >= 3) {
            throw new RuntimeException("User has already borrowed the maximum limit of 3 books.");
        }

        // Decrease available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        // Create transaction
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        
        return borrowRepository.save(borrow);
    }

    @Transactional
    public Borrow returnBook(Long borrowId, String userEmail) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + borrowId));

        if (!borrow.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You are not authorized to return this book.");
        }

        if (borrow.getReturnDate() != null) {
            throw new RuntimeException("Book is already returned.");
        }

        borrow.setReturnDate(LocalDate.now());

        long daysBorrowed = ChronoUnit.DAYS.between(borrow.getBorrowDate(), borrow.getReturnDate());
        double penalty = 0.0;
        
        if (daysBorrowed > MAX_BORROW_DAYS) {
            long lateDays = daysBorrowed - MAX_BORROW_DAYS;
            penalty = lateDays * PENALTY_PER_DAY;
        }
        
        borrow.setPenaltyAmount(penalty);

        // Increase available copies
        Book book = borrow.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public List<Borrow> getActiveBorrows(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
        return borrowRepository.findByUserIdAndReturnDateIsNull(user.getId());
    }
}
