package com.gergelytamas.simplechat.service.mapper;

import com.gergelytamas.simplechat.model.User;
import com.gergelytamas.simplechat.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
