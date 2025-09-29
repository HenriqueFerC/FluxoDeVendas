package br.com.learning.UserKafka.controller;

import br.com.learning.UserKafka.domain.Items;
import br.com.learning.UserKafka.domain.User;
import br.com.learning.UserKafka.dto.ItemsDto.RegisterItemsDto;
import br.com.learning.UserKafka.dto.UserDto.DetailsUserDto;
import br.com.learning.UserKafka.dto.UserDto.RegisterUserDto;
import br.com.learning.UserKafka.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("jsonKafkaTemplate")
    private KafkaTemplate<Long, Items> jsonKafkaTemplate;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetailsUserDto> registerUser(@RequestBody RegisterUserDto userDto, UriComponentsBuilder uriBuilder) {
        var user = new User(userDto);
        userRepository.save(user);
        var uri = uriBuilder.path("user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUserDto(user));
    }

    @PostMapping("buy")
    public ResponseEntity<Void> buyItems(@RequestBody RegisterItemsDto itemsDto) {
        var item = new Items(itemsDto);
        jsonKafkaTemplate.send("user-topic", item);
        return ResponseEntity.ok().build();
    }
}
