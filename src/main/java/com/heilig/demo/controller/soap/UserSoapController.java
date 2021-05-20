package com.heilig.demo.controller.soap;

import com.heilig.demo.annotation.LogExecutionTime;
import com.heilig.demo.controller.api.UserApi;
import com.heilig.demo.service.UserService;
import com.heilig.demo.xsd.UserDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;

import static com.heilig.demo.controller.soap.config.WebServiceConfig.NAMESPACE;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Endpoint
public class UserSoapController implements UserApi {

    @NonNull
    private final UserService userService;


    @Override
    public List<UserDto> retrieveUsers() {

        return userService.retrieveUsers().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @LogExecutionTime
    @PayloadRoot(namespace = NAMESPACE, localPart = "retrieveUsers")
    @ResponsePayload
    public JAXBElement<com.heilig.demo.xsd.UserDtoList> retrieveUsers(@RequestPayload com.heilig.demo.xsd.UserDtoFilters filters) {

        var objectFactory = new com.heilig.demo.xsd.ObjectFactory();
        var userDtoList = new com.heilig.demo.xsd.UserDtoList();
        userDtoList.getUsersDto().addAll(retrieveUsers());
        return objectFactory.createUsersDto(userDtoList);
    }
}
