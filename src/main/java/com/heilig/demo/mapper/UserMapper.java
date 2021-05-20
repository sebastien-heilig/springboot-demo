package com.heilig.demo.mapper;

import com.heilig.demo.model.User;
import com.heilig.demo.util.DateUtils;
import com.heilig.demo.xsd.UserDto;
import java.time.LocalDate;
import java.time.Period;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "parseDate")
  User map(UserDto dto);

  @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "formatDate")
  UserDto map(User model);

  @AfterMapping
  default void afterMapping(@MappingTarget UserDto userDto, User model) {

    userDto.setAge(Period.between(model.getBirthDate(), LocalDate.now()).getYears());
  }

  @Named("parseDate")
  default LocalDate parseDate(String localDateString) {

    return DateUtils.parse(localDateString);
  }

  @Named("formatDate")
  default String formatDate(LocalDate localDate) {

    return DateUtils.format(localDate);
  }
}
