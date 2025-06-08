package pl.wsb.controller;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;
import pl.wsb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 1. Wylistowanie ID i nazw użytkowników
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 2. Pobranie szczegółów użytkownika po ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 3. Pobranie szczegółów użytkownika po e-mailu (pełna wersja)
    @GetMapping("/by-email")
    public ResponseEntity<UserDetailDto> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // 4. Wyszukiwanie użytkowników po fragmencie e-maila (ignorując wielkość liter)
    @GetMapping("/search-by-email")
    public ResponseEntity<List<UserDto>> searchUsersByEmail(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsersByEmail(query));
    }

    // 5. Wyszukiwanie użytkowników starszych niż X lat
    @GetMapping("/older-than")
    public ResponseEntity<List<UserDto>> getUsersOlderThan(@RequestParam int age) {
        return ResponseEntity.ok(userService.getUsersOlderThan(age));
    }

    // 6. Tworzenie nowego użytkownika
    @PostMapping
    public ResponseEntity<UserDetailDto> createUser(@RequestBody UserDetailDto newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    // 7. Aktualizacja istniejącego użytkownika
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserDetailDto updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    // 8. Usuwanie użytkownika po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
