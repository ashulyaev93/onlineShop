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
            @Mapping(source="user.firstname", target="firstname"),
            @Mapping(source="user.lastname", target="lastname"),
            @Mapping(source="user.password", target="password"),
            @Mapping(source="user.email", target="email"),
            @Mapping(source="user.role", target="role"),
            @Mapping(source="user.status", target="status"),
            @Mapping(source="user.orders", target="orders")
    })
    UserDTO userToUserDTO(User user);
    @Mappings({
            @Mapping(source="userDTO.id", target="id"),
            @Mapping(source="userDTO.username", target="username"),
            @Mapping(source="userDTO.firstname", target="firstname"),
            @Mapping(source="userDTO.lastname", target="lastname"),
            @Mapping(source="userDTO.password", target="password"),
            @Mapping(source = "userDTO.email", target = "email"),
            @Mapping(source = "userDTO.role", target = "role"),
            @Mapping(source = "userDTO.status", target = "status"),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "createdDate", ignore = true)
    })
    User userDTOtoUser(UserDTO userDTO);
}
