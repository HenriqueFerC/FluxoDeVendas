package br.com.learning.UserKafka.domain;

import br.com.learning.UserKafka.dto.UserDto.RegisterUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "BALANCE")
    private double balance;

    public User(RegisterUserDto userDto) {
        login = userDto.login();
        password = userDto.password();
        name = userDto.name();
        cpf = userDto.cpf();
        balance = userDto.balance();
    }
}
