package com.example.NewProject.repository;

import com.example.NewProject.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
  Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);

  void deleteById(Long id);
}
