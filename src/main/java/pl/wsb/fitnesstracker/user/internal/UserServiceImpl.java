package pl.wsb.fitnesstracker.user.internal;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;
import pl.wsb.mapper.UserMapper;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;
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
        return UserMapper.toUserDetailDto(user);
    }

    @Override
    public UserDetailDto getUserByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return UserMapper.toUserDetailDto(user);
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
        User user = UserMapper.toUserEntity(userDetailDto);
        User saved = userRepository.save(user);
        return UserMapper.toUserDetailDto(saved);
    }

    @Override
    public UserDetailDto updateUser(Long id, UserDetailDto userDetailDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

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
        return UserMapper.toUserDetailDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
