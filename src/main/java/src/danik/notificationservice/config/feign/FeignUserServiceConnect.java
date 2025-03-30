package src.danik.notificationservice.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import src.danik.notificationservice.dto.UserDto;

import java.util.List;

@FeignClient(name = "user-microservice", url = "http://localhost:8080/api/v1", configuration = FeignConfig.class)
public interface FeignUserServiceConnect {
    @GetMapping("/users")
    List<UserDto> getAllUsers();

    @GetMapping("/users/{id}")
    UserDto getUserById(@PathVariable Long id);

    @PostMapping(value = "/users/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getAllUsersByIds(@RequestBody List<Long> ids);
}
