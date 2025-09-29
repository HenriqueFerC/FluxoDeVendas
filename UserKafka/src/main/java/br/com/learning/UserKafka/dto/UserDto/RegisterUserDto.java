package br.com.learning.UserKafka.dto.UserDto;

public record RegisterUserDto(String login, String password, String name, String cpf, double balance) {
}
