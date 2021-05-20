package com.heilig.demo.controller.rest;

import com.heilig.demo.annotation.LogExecutionTime;
import com.heilig.demo.controller.api.UserApi;
import com.heilig.demo.service.UserService;
import com.heilig.demo.xsd.UserDto;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestController implements UserApi {

  @NonNull
  private final UserService userService;

  @LogExecutionTime
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

    return ResponseEntity.ok(createUser(userService, userDto));
  }

  @LogExecutionTime
  @GetMapping
  public ResponseEntity<List<UserDto>> retrieveUsersResponse() {

    return ResponseEntity.ok(retrieveUsers(userService));
  }
}
