package com.heilig.demo.controller.rest;

import com.heilig.demo.annotation.LogExecutionTime;
import com.heilig.demo.controller.api.UserApi;
import com.heilig.demo.service.UserService;
import com.heilig.demo.xsd.UserDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    //region UserApi methods

    @Override
    public List<UserDto> retrieveUsers() {

        return userService.retrieveUsers().stream().map(this::map).collect(Collectors.toList());
    }

    @LogExecutionTime
    @PostMapping
    @Override
    public UserDto createUser(@RequestBody UserDto userDto) {

        Objects.requireNonNull(userDto, "The DTO you want to persist must be not null");
        log.debug("dto : {}", userDto.toString());
        var user = map(userDto);
        return map(userService.createUser(user));
    }
    //endregion

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<List<UserDto>> retrieveUsersResponse(){

        return ResponseEntity.ok(retrieveUsers());
    }
}
