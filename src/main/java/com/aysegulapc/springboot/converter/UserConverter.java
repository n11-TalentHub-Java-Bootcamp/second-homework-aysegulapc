package com.aysegulapc.springboot.converter;

import com.aysegulapc.springboot.dto.UserDto;
import com.aysegulapc.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "name", source = "userName")
    List<UserDto> convertUserListToUserDtoList(List<User> user);

    @Mapping(target = "name", source = "userName")
    UserDto convertUserToUserDto(User user);

    @Mapping(target = "userName", source = "name")
    @Mapping(target = "id", source = "id")
    User convertUserDtoToUser(UserDto userDto);
}
