package pl.wsb.fitnesstracker.user.api;

import pl.wsb.doto.UserDetailDto;
import pl.wsb.doto.UserDto;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    List<UserDto> getAllUsers();

    UserDetailDto getUserById(Long id);

    List<UserDto> searchUsersByEmail(String emailFragment);

    List<UserDto> getUsersOlderThan(int age);

    UserDetailDto createUser(UserDetailDto userDetailDto);

    UserDetailDto updateUser(Long id, UserDetailDto userDetailDto);

    void deleteUser(Long id);
}
