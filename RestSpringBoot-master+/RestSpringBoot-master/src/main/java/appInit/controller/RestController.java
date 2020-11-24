package appInit.controller;

import appInit.DTO.UserDTO;
import appInit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("/rest/admin")
public class RestController {

    private UserService service;

    // ResponseEntity + HttpStatus (ok)
    // ResponseEntity является оберткой для ответа и дополнительно для HTTP заголовков и кода статуса.
    // HttpStatus - Enum с перечислением кода состояния HTTP

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<String>> getAllRoles() {
        return new ResponseEntity<>(service.getNameRoles(), HttpStatus.OK);
    }

    @PostMapping("/users") //requestparam vs requestbody?
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        service.addUser(user);
        return new ResponseEntity<>(service.getUserByName(user.getName()), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
        service.editUser(user);
        return new ResponseEntity<>(service.getUserByName(user.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
