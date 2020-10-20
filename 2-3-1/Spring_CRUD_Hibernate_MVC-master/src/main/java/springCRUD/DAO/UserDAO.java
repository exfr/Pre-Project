package springCRUD.DAO;

import springCRUD.model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    List<User> getAllUsers();
    List<User> getUserListById(Long id);
    User getUserById(Long id);
}
