package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.EnfermeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnfermeiroRepository extends JpaRepository<EnfermeiroModel, String> {

    public Optional<EnfermeiroModel> findByInscricaoCoren(String inscricaoCoren);
}
