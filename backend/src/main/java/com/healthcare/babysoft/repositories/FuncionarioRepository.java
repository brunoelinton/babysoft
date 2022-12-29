package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, String> {

    Optional<FuncionarioModel> findByCpf(String cpf);

    Optional<FuncionarioModel> findByEmail(String email);
}
