package com.gergelytamas.simplechat.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class MessageDTO implements Serializable {

    private Integer id;

    @NotNull
    private Instant messageDate;

    @NotNull
    private String messageText;

    private Integer messageFromId;

    private Integer messageToId;
}
