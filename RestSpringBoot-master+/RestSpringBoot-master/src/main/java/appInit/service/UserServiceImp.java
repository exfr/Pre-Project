package appInit.service;

import appInit.DTO.UserDTO;
import appInit.model.Role;
import appInit.model.User;
import appInit.repository.RoleRepo;
import appInit.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("UserService")
public class UserServiceImp implements UserService, UserDetailsService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            users.add(getDtoFromUser(user));
        }
        return users;
    }

    public UserDTO getUserById(Long id) {
        return getDtoFromUser(userRepo.findById(id).get());
    }

    public UserDTO getUserByName(String login) {
        return getDtoFromUser(userRepo.findByName(login));
    }

    public void addUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(getUserFromDTO(user));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public void editUser(UserDTO user) {
        User userUpd = userRepo.findById(user.getId()).get();
        userUpd.setPassword(passwordEncoder.encode(user.getPassword()));
        userUpd.setRoles(getSetOfRoles(user.getRoles()));
        userUpd.setName(user.getName());
        userRepo.save(userUpd);
    }

    public Set<String> getNameRoles() {
        Set<String> nameRoles = new HashSet<>();
        for (Role role : roleRepo.findAll()) {
            nameRoles.add(role.getRole());
        }
        return nameRoles;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepo.findByName(login);
    }

    private User getUserFromDTO(UserDTO userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getPassword()
                , userDto.getEmail(), getSetOfRoles(userDto.getRoles()));
    }

    private UserDTO getDtoFromUser(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getPassword(),
                user.getEmail(), getSetOfString(user.getRoles()));
    }

    private Set<Role> getSetOfRoles(Set<String> nameRoles) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : nameRoles) {
            roles.add(roleRepo.findByRole(roleName));
        }
        return roles;
    }

    public Set<String> getSetOfString(Set<Role> roles) {
        Set<String> nameRoles = new HashSet<>();
        for (Role role : roles) {
            nameRoles.add(role.getRole());
        }
        return nameRoles;
    }
}
