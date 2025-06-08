package pl.wsb.service;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;

import java.util.List;

public interface UserService {

    // Pobranie listy wszystkich użytkowników (tylko ID i nazwa)
    List<UserDto> getAllUsers();

    // Pobranie szczegółów użytkownika po ID
    UserDetailDto getUserById(Long id);

    // Pobranie szczegółów użytkownika po e-mailu
    UserDetailDto getUserByEmail(String email);

    // Wyszukiwanie użytkowników po fragmencie e-maila (ignorując wielkość liter)
    List<UserDto> searchUsersByEmail(String emailFragment);

    // Wyszukiwanie użytkowników starszych niż podany wiek
    List<UserDto> getUsersOlderThan(int age);

    // Tworzenie nowego użytkownika
    UserDetailDto createUser(UserDetailDto userDetailDto);

    // Aktualizacja użytkownika (wg ID)
    UserDetailDto updateUser(Long id, UserDetailDto userDetailDto);

    // Usuwanie użytkownika po ID
    void deleteUser(Long id);
}
