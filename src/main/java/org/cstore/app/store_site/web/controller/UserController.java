package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.cstore.app.store_site.entity.RoleType;
import org.cstore.app.store_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.entity.AUser;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<AUser> getUsers() {
        log.info("process=get-users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AUser> getUser(@PathVariable Long id) {
        log.info("process=get-user, user_id={}", id);
        Optional<AUser> user = userService.getUserById(id);
        return user.map( u -> ResponseEntity.ok(u))
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Optional<AUser> createUser(@RequestBody AUser user) {
        log.info("process=create-user, user_email={}", user.getEmail());
        return userService.createUser(Optional.of(user), RoleType.StoreAdmin);
    }

    @PutMapping("/{id}")
    public Optional<AUser>  updateUser(@PathVariable Long id, @RequestBody AUser user) {
        log.info("process=update-user, UserId={}", id);
        user.setUserId(id);
        return userService.updateUser(Optional.of(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("process=delete-user, user_id={}", id);
        userService.deleteUser(id);
    }

}
