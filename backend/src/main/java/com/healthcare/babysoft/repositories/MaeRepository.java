package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.MaeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaeRepository extends JpaRepository<MaeModel, String> {

    public Optional<MaeModel> findByCpf(String cpf);
}
