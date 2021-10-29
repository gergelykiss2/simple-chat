package com.gergelytamas.simplechat.controller;

import com.gergelytamas.simplechat.service.MessageService;
import com.gergelytamas.simplechat.service.dto.MessageDTO;
import com.gergelytamas.simplechat.service.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/messages")
public class MessageController {

    private final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    private final MessageMapper messageMapper;

    public MessageController(final MessageService messageService, final MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody final MessageDTO messageDTO)
            throws URISyntaxException {
        log.debug("REST request to save Message : {}", messageDTO);

        if (messageDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        final MessageDTO result =
                messageMapper.toDto(this.messageService.save(messageMapper.toEntity(messageDTO)));
        return ResponseEntity.created(new URI("/api/messages/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(
            @Valid @RequestBody final MessageDTO messageDTO, @PathVariable final Integer id) {
        log.debug("REST request to update Message : {}", messageDTO);

        if (messageDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .body(messageMapper.toDto(this.messageService.update(id, messageMapper.toEntity(messageDTO))));
    }

    @GetMapping("")
    public ResponseEntity<List<MessageDTO>> findAll() {
        log.debug("REST request to get all Messages");
        return ResponseEntity.ok()
                .body(
                        this.messageService.findAll().stream()
                                .map(messageMapper::toDto)
                                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable final Integer id) {
        log.debug("REST request to get Message : {}", id);
        return ResponseEntity.of(this.messageService.findById(id).map(messageMapper::toDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Integer id) {
        log.debug("REST request to delete Message : {}", id);
        this.messageService.delete(id);
        return ResponseEntity.noContent()
                .header("X-SimpleChatApp-alert", "Message with id " + id + " has been deleted.")
                .build();
    }
}
