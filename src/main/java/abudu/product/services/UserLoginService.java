package abudu.product.services;

import abudu.product.dto.UserDTO;
import abudu.product.mappers.UserMapper;
import abudu.product.models.User;
import abudu.product.repositories.UserRepository;
import abudu.product.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (SecurityUtil.validatePassword(password, user.getPassword())) {
                return userMapper.mapToDTO(user);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}