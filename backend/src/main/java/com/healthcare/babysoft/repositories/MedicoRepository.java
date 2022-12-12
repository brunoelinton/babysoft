package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoModel, String> {

    public Optional<MedicoModel> findByCrm(String crm);
}
