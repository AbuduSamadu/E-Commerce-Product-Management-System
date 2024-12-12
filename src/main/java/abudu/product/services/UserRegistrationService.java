package abudu.product.services;


import abudu.product.dto.UserDTO;
import abudu.product.exceptions.ResourcesAlreadyExitsException;
import abudu.product.mappers.UserMapper;
import abudu.product.models.User;
import abudu.product.repositories.UserRepository;
import abudu.product.utilities.SecurityUtil;
import abudu.product.utilities.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        if (!ValidationUtil.isValidEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new ResourcesAlreadyExitsException("Email already exists");
        }

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new ResourcesAlreadyExitsException("Username already exists");
        }

        User user =  userMapper.mapToEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPassword(userDTO.getPassword());
        user.setActive(true);

        // Generate and set the salt
        byte[] salt = SecurityUtil.generateSalt();
        user.setSalt(salt);

        // Hash the password before saving
        String hashedPassword = SecurityUtil.hashPassword(userDTO.getPassword(), salt);
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return userMapper.mapToDTO(savedUser);
    }
}
