package com.healthcare.babysoft.repositories;

import com.healthcare.babysoft.models.EquipeMedicaModel;
import com.healthcare.babysoft.models.pk.EquipeMedicaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeMedicaRepository extends JpaRepository<EquipeMedicaModel, EquipeMedicaPK> {
}
