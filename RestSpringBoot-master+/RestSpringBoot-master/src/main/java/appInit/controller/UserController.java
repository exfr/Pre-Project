package appInit.controller;

import appInit.DTO.UserDTO;
import appInit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("getCurrentUser") // na js s tabloy usera
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        UserDTO currentUser = service.getUserByName(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}