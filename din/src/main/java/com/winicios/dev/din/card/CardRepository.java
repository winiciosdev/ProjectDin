package com.winicios.dev.din.card;

import com.winicios.dev.din.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {


    List<Card> findAllByUser(User user);

    Optional<Card> findByIdAndUser(String id, User user);
}