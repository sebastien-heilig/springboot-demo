package com.heilig.demo.controller.api;

import com.heilig.demo.mapper.UserMapper;
import com.heilig.demo.model.User;
import com.heilig.demo.xsd.UserDto;

import java.util.List;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
public interface UserApi {

    List<UserDto> retrieveUsers();
    UserDto createUser(UserDto userDto);

    default User map(UserDto userDto){
        return UserMapper.INSTANCE.map(userDto);
    }
    default UserDto map(User user){
        return UserMapper.INSTANCE.map(user);
    }

}
