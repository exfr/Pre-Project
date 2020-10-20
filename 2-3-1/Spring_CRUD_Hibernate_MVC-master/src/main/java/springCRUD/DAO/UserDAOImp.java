package springCRUD.DAO;

import springCRUD.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        //language=HQL
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public List<User> getUserListById(Long id) {
       return entityManager.createQuery("FROM User WHERE id =" + id, User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
       return entityManager.find(User.class, id);
    }
}