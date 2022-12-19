package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.PartoModel;
import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartoRepository extends JpaRepository<PartoModel, Long> {

    public Optional<PartoModel> findByRecemNascido(RecemNascidoModel recemNascido);
}
