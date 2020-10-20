package springCRUD.web.controller;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springCRUD.model.User;
import springCRUD.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index() {
        return "allUsers";
    }

    @GetMapping("/users")
    public String usersList(ModelMap model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "allUsers";
    }

    @PostMapping("/searchForm")
    public String search() {
        return "searchUser";
    }

    @GetMapping("/findUser")
    public String findUserByID(ModelMap model, Long id) {
           model.addAttribute("findUser", userService.getUserListById(id));
        return "findUser";
    }

    @PostMapping("/addForm")
    public String addUser() {
        return "addUser";
    }

    @GetMapping("/add")
    public String viewAddedUser(User user, ModelMap model) {
        userService.addUser(user);
        model.addAttribute("userList", userService.getAllUsers());
        return "allUsers";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam @NonNull Long id, ModelMap model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.getAllUsers());
        return "allUsers";
    }

    @PostMapping("/updateForm")
    public String editUser(User user, ModelMap model) {
        model.addAttribute("editUser", user);
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(User user, ModelMap model) {
        userService.updateUser(user);
        model.addAttribute("userList", userService.getAllUsers());
        return "allUsers";
    }
}