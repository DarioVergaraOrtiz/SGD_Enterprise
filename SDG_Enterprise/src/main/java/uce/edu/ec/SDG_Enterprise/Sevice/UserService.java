package uce.edu.ec.SDG_Enterprise.Sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.SDG_Enterprise.Model.User;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public User save(User usuario) {
        return userRepository.save(usuario);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public String getRolForLogin(String username, String password) {
        Optional<User> usuarioOptional = userRepository.findByUsernameAndPassword(username, password);

        if (usuarioOptional.isPresent()) {
            User user = usuarioOptional.get();
            return user.getRol();
        } else {
            return null;
        }
    }

}
