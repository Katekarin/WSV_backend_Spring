package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.doto.UserDetailDto;
import pl.wsb.doto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<pl.wsb.doto.UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDetailDto> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/search-by-email")
    public ResponseEntity<List<pl.wsb.doto.UserDto>> searchUsersByEmail(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsersByEmail(query));
    }

    @GetMapping("/older-than")
    public ResponseEntity<List<UserDto>> getUsersOlderThan(@RequestParam int age) {
        return ResponseEntity.ok(userService.getUsersOlderThan(age));
    }

    @PostMapping
    public ResponseEntity<UserDetailDto> createUser(@RequestBody UserDetailDto newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserDetailDto updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}