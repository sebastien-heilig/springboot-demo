package com.heilig.demo.controller.soap;

import static com.heilig.demo.controller.soap.config.WebServiceConfig.NAMESPACE;

import com.heilig.demo.annotation.LogExecutionTime;
import com.heilig.demo.controller.api.UserApi;
import com.heilig.demo.service.UserService;
import com.heilig.demo.xsd.ObjectFactory;
import com.heilig.demo.xsd.UserDto;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
  private ObjectFactory objectFactory;

  @PostConstruct
  private void initObjectFactory() {

    this.objectFactory = new ObjectFactory();
  }

  @LogExecutionTime
  @PayloadRoot(namespace = NAMESPACE, localPart = "createUser")
  @ResponsePayload
  public JAXBElement<com.heilig.demo.xsd.UserDto> createUser(@RequestPayload UserDto userDto) {

    return objectFactory.createUserDto(createUser(userService, userDto));
  }

  @LogExecutionTime
  @PayloadRoot(namespace = NAMESPACE, localPart = "retrieveUsers")
  @ResponsePayload
  public JAXBElement<com.heilig.demo.xsd.UserDtoList> retrieveUsers(@RequestPayload com.heilig.demo.xsd.UserDtoFilters filters) {

    var userDtoList = new com.heilig.demo.xsd.UserDtoList();
    userDtoList.getUsersDto().addAll(retrieveUsers(userService));
    return objectFactory.createUsersDto(userDtoList);
  }
}
