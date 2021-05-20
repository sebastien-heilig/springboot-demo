package com.heilig.demo.controller.api;

import com.heilig.demo.mapper.UserMapper;
import com.heilig.demo.model.User;
import com.heilig.demo.service.UserService;
import com.heilig.demo.xsd.UserDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
public interface UserApi {

  Logger log = LoggerFactory.getLogger(UserApi.class);

  default List<UserDto> retrieveUsers(UserService userService) {

    return userService.retrieveUsers().stream().map(this::toDto).collect(Collectors.toList());
  }

  default UserDto createUser(UserService userService, UserDto userDto) {

    Objects.requireNonNull(userDto, "The DTO you want to persist must be not null");
    log.debug("dto : {}", userDto);
    var user = toModel(userDto);
    return toDto(userService.createUser(user));
  }

  default User toModel(UserDto userDto) {
    return UserMapper.INSTANCE.map(userDto);
  }

  default UserDto toDto(User user) {
    return UserMapper.INSTANCE.map(user);
  }

}
