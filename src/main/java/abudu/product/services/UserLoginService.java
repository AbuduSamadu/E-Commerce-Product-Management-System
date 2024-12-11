package abudu.product.services;

import abudu.product.dto.UserDTO;
import abudu.product.exceptions.InvalidCredentialException;
import abudu.product.mappers.UserMapper;
import abudu.product.models.User;
import abudu.product.repositories.UserRepository;
import abudu.product.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserLoginService {

    private static final Logger LOGGER = Logger.getLogger(UserLoginService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "users", key = "#username")
    public UserDTO loginUser(String username, String password) {
        try {
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                byte[] salt = user.getSalt(); // Retrieve the salt
                if (SecurityUtil.validatePassword(password, user.getPassword(), salt)) {
                    return userMapper.mapToDTO(user);
                } else {
                    throw new InvalidCredentialException("Invalid credentials");
                }
            } else {
                throw new InvalidCredentialException("Invalid credentials");
            }
        } catch (InvalidCredentialException e) {
            LOGGER.log(Level.WARNING, "Invalid credentials: {0}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Internal server error: {0}", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}