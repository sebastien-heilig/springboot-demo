package com.heilig.demo.service;

import com.heilig.demo.model.User;
import com.heilig.demo.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  @NonNull
  private final UserRepository userRepository;

  public List<User> retrieveUsers() {

    return userRepository.findAll();
  }

  public User createUser(User user) {

    Objects.requireNonNull(user, "Cannot persist a null user");
    user.setId(null);
    return userRepository.save(user);
  }
}
