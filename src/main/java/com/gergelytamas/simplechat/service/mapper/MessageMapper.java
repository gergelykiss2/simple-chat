package com.gergelytamas.simplechat.service.mapper;

import com.gergelytamas.simplechat.model.Message;
import com.gergelytamas.simplechat.service.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "messageFrom.id", target = "messageFromId")
    @Mapping(source = "messageTo.id", target = "messageToId")
    MessageDTO toDto(Message message);

    Message toEntity(MessageDTO messageDTO);

}
