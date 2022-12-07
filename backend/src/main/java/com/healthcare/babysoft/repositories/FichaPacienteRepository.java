package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.FichaPacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FichaPacienteRepository extends JpaRepository<FichaPacienteModel, String> {

    public Optional<FichaPacienteModel> findByCpf(String cpf);
}
