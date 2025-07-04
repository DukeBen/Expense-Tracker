package com.bencompany.expensetracker.repository;

import com.bencompany.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}

