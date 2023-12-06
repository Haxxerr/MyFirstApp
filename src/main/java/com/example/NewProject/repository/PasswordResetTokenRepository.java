package com.example.NewProject.repository;

import com.example.NewProject.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

  Optional<PasswordResetToken> findByPasswordResetToken(String passwordResetToken);

  void deleteById(Long id);
}
