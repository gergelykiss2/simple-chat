package com.gergelytamas.simplechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "message", schema = "public")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "message_date", nullable = false)
    private Instant messageDate;

    @NotNull
    @Column(name = "message_text", nullable = false)
    private String messageText;

    @ManyToOne
    @JsonIgnoreProperties(value = "messages", allowSetters = true)
    private User messageFrom;

    @ManyToOne
    @JsonIgnoreProperties(value = "messages", allowSetters = true)
    private User messageTo;

}
