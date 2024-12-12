package abudu.product.services;

import abudu.product.dto.UserDTO;
import abudu.product.exceptions.InvalidCredentialException;
import abudu.product.mappers.UserMapper;
import abudu.product.models.User;
import abudu.product.repositories.UserRepository;
import abudu.product.utilities.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public UserDTO loginUser( String email, String password) {
        try {
            if (email == null || password == null) {
                throw new InvalidCredentialException("Invalid credentials");
            }

            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                byte[] salt = user.getSalt(); // Retrieve the salt
                if (SecurityUtil.validatePassword(password, user.getPassword(), salt)) {
                    user.setActive(true);
                    userRepository.save(user);
                    return userMapper.mapToDTO(user);
                } else {
                    throw new InvalidCredentialException("Invalid credentials");
                }
            } else {
                throw new InvalidCredentialException("Invalid credentials");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while logging in: {0}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while logging in");
        }
    }
}