package org.kodluyoruz.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private Map<UUID, User> users = new HashMap<>();

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User save(@RequestBody UserDto userDto) {

        User u = new User(UUID.randomUUID(), userDto.getName(), userDto.getAge());
        users.put(u.getId(), u);
        return u;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User get(@PathVariable UUID id) {
        return users.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> get(@RequestParam("name") String name) {
        return users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName().startsWith(name))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
