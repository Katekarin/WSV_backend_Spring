package pl.wsb.service;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDetailDto getUserById(Long id);

    UserDetailDto getUserByEmail(String email);

    List<UserDto> searchUsersByEmail(String emailFragment);

    List<UserDto> getUsersOlderThan(int age);

    UserDetailDto createUser(UserDetailDto userDetailDto);

    UserDetailDto updateUser(Long id, UserDetailDto userDetailDto);

    void deleteUser(Long id);
}
