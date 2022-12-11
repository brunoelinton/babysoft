package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.FichaPacienteDTO;
import com.healthcare.babysoft.dtos.MaeDTO;
import com.healthcare.babysoft.enums.TipoSanguineo;
import com.healthcare.babysoft.models.FichaPacienteModel;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.repositories.FichaPacienteRepository;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class FichaPacienteService {

    @Autowired
    private FichaPacienteRepository fichaPacienteRepository;

    @Autowired
    private MaeRepository maeRepository;

    @Autowired
    private MaeService maeService;

    @Transactional
    public FichaPacienteDTO preencherFichaPaciente(String cpfPaciente, FichaPacienteDTO fichaPacienteDTO) {
        Optional<FichaPacienteModel> fichaPacienteModelOptional = fichaPacienteRepository.findByCpf(cpfPaciente);

        if (fichaPacienteModelOptional.isPresent()) {
            throw new ResourceConflictPersistence("Ficha com CPF informado já presente no sistema.");
        }

        Optional<MaeModel> obj = maeRepository.findByCpf(cpfPaciente);
        MaeModel maeModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há mãe com esse CPF"));

        return new FichaPacienteDTO(maeModel.preencherFichaPaciente(fichaPacienteDTO));
//        FichaPacienteModel fichaPacienteModel = new FichaPacienteModel();
//        System.out.println("TipoSanguineo: " + fichaPacienteDTO.getTipoSanguineo());
//        System.out.println("SoroPositivo: " + fichaPacienteDTO.getSoroPositivo().toString());
//        System.out.println("Hipertensão: " + fichaPacienteDTO.getHipertensao().toString());
//        System.out.println("MedicaçãoControlada: " + fichaPacienteDTO.getMedicacaoControlada().toString());
//        System.out.println("CPF: " + fichaPacienteDTO.getCpf());
//        System.out.println("Passou");

//        fichaPacienteModel.setMae(maeModel);
//        fichaPacienteModel.setTipoSanguineo(fichaPacienteDTO.getTipoSanguineo());
//        fichaPacienteModel.setSoroPositivo(fichaPacienteDTO.getSoroPositivo());
//        fichaPacienteModel.setHipertensao(fichaPacienteDTO.getHipertensao());
//        fichaPacienteModel.setDiabetes(fichaPacienteDTO.getDiabetes());
//        fichaPacienteModel.setMedicacaoControlada(fichaPacienteDTO.getMedicacaoControlada());
//        fichaPacienteModel.setPeso(fichaPacienteDTO.getPeso());
//        fichaPacienteModel.setAltura(fichaPacienteDTO.getAltura());
//        fichaPacienteModel = fichaPacienteRepository.save(fichaPacienteModel);
//        return new FichaPacienteDTO(fichaPacienteModel);
    }

    @Transactional(readOnly = true)
    public Page<FichaPacienteDTO> buscarTodasFichas(Pageable pageable) {
        Page<FichaPacienteModel> lista = fichaPacienteRepository.findAll(pageable);
        return lista.map(ficha -> new FichaPacienteDTO(ficha));
    }

    @Transactional(readOnly = true)
    public FichaPacienteDTO buscarFichaPaciente(String cpf) {
        Optional<FichaPacienteModel> obj = fichaPacienteRepository.findByCpf(cpf);
        FichaPacienteModel fichaPacienteModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há ficha de paciente com esse CPF."));
        return new FichaPacienteDTO(fichaPacienteModel);
    }
}
