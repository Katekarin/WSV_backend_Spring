package pl.wsb.service.impl;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;
import pl.wsb.mapper.UserMapper;
import pl.wsb.persistence.User;
import pl.wsb.respository.UserRepository;
import pl.wsb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        return userMapper.toUserDetailDto(user);
    }

    @Override
    public UserDetailDto getUserByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return userMapper.toUserDetailDto(user);
    }

    @Override
    public List<UserDto> searchUsersByEmail(String emailFragment) {
        if (!StringUtils.hasText(emailFragment)) {
            return List.of();
        }
        return userRepository.findByEmailContainingIgnoreCase(emailFragment)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersOlderThan(int age) {
        LocalDate dateThreshold = LocalDate.now().minusYears(age);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getBirthDate() != null && user.getBirthDate().isBefore(dateThreshold))
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailDto createUser(UserDetailDto userDetailDto) {
        User user = userMapper.toUser(userDetailDto);
        User saved = userRepository.save(user);
        return userMapper.toUserDetailDto(saved);
    }

    @Override
    public UserDetailDto updateUser(Long id, UserDetailDto userDetailDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        // Aktualizacja wybranych pól
        if (StringUtils.hasText(userDetailDto.firstName())) {
            user.setFirstName(userDetailDto.firstName());
        }
        if (StringUtils.hasText(userDetailDto.lastName())) {
            user.setLastName(userDetailDto.lastName());
        }
        if (StringUtils.hasText(userDetailDto.email())) {
            user.setEmail(userDetailDto.email());
        }
        if (userDetailDto.birthDate() != null) {
            user.setBirthDate(userDetailDto.birthDate());
        }

        User updated = userRepository.save(user);
        return userMapper.toUserDetailDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
