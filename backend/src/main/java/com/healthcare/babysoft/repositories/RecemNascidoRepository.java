package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecemNascidoRepository extends JpaRepository<RecemNascidoModel, RecemNascidoPK> {

    Optional<RecemNascidoModel> findByRecemNascidoId(RecemNascidoPK recemNascidoPK);
}
