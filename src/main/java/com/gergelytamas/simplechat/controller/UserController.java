package com.gergelytamas.simplechat.controller;

import com.gergelytamas.simplechat.service.UserService;
import com.gergelytamas.simplechat.service.dto.UserDTO;
import com.gergelytamas.simplechat.service.mapper.UserMapper;
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
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserDTO userDTO)
            throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        final UserDTO result =
                userMapper.toDto(this.userService.save(userMapper.toEntity(userDTO)));
        return ResponseEntity.created(new URI("/api/users/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
            @Valid @RequestBody final UserDTO userDTO, @PathVariable final Integer id) {
        log.debug("REST request to update User : {}", userDTO);

        if (userDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .body(userMapper.toDto(this.userService.update(id, userMapper.toEntity(userDTO))));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAll() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok()
                .body(
                        this.userService.findAll().stream()
                                .map(userMapper::toDto)
                                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable final Integer id) {
        log.debug("REST request to get User : {}", id);
        return ResponseEntity.of(this.userService.findById(id).map(userMapper::toDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Integer id) {
        log.debug("REST request to delete User : {}", id);
        this.userService.delete(id);
        return ResponseEntity.noContent()
                .header("X-SimpleChatApp-alert", "User with id " + id + " has been deleted.")
                .build();
    }
}
