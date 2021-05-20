package com.heilig.demo.repository;

import com.heilig.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
