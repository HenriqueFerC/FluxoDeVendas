package br.com.learning.UserKafka.dto.UserDto;

import br.com.learning.UserKafka.domain.User;

public record DetailsUserDto(String login, String password, String name, String cpf, double balance) {
    public DetailsUserDto(User user){
        this(user.getLogin(), user.getPassword(), user.getName(), user.getCpf(), user.getBalance());
    }
}
