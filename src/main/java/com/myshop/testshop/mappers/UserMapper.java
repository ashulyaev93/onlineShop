package com.myshop.testshop.mappers;

import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("userToUserDTO")
    @Mappings({
            @Mapping(source="user.id", target="id"),
            @Mapping(source="user.username", target="username"),
            @Mapping(source="user.fullname", target="fullname"),
            @Mapping(source="user.password", target="password")
    })
    UserDTO userToUserDTO(User user);
    @Mappings({
            @Mapping(source="userDTO.id", target="id"),
            @Mapping(source="userDTO.username", target="username"),
            @Mapping(source="userDTO.fullname", target="fullname"),
            @Mapping(source="userDTO.password", target="password"),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "createdDate", ignore = true)
    })
    User userDTOtoUser(UserDTO userDTO);
}
