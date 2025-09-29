package br.com.learning.UserKafka.repository;

import br.com.learning.UserKafka.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
