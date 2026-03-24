package com.library.management.repository;

import com.library.management.entity.Borrow;
import com.library.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    
    // Count active borrows for a user
    int countByUserAndReturnDateIsNull(User user);

    // Get active borrows for a user
    List<Borrow> findByUserIdAndReturnDateIsNull(Long userId);
}
