package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.EspecialidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadeRepository extends JpaRepository<EspecialidadeModel, Integer> {

    public Optional<EspecialidadeModel> findByNome(String nome);
}
