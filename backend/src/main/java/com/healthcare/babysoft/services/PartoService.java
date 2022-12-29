package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.PartoDTO;
import com.healthcare.babysoft.models.*;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;
import com.healthcare.babysoft.repositories.EquipePartoRepository;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.repositories.PartoRepository;
import com.healthcare.babysoft.repositories.RecemNascidoRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PartoService {

    @Autowired
    private PartoRepository partoRepository;

    @Autowired
    private RecemNascidoRepository recemNascidoRepository;

    @Autowired
    private MaeRepository maeRepository;

    @Autowired
    private EquipePartoRepository equipePartoRepository;

    @Transactional(readOnly = true)
    public Page<PartoDTO> buscarTodosPartos(Pageable pageable) {
        Page<PartoModel> partos = partoRepository.findAll(pageable);
        return partos.map(parto -> new PartoDTO(parto));
    }

    @Transactional(readOnly = true)
    public PartoDTO buscarUmParto(Long partoId) {
        Optional<PartoModel> obj = partoRepository.findById(partoId);
        PartoModel parto = obj.orElseThrow(() -> new ResourceNotFoundException("Parto com ID informado não presente no sistema."));
        return new PartoDTO(parto);
    }

    @Transactional
    public PartoDTO cadastrarParto(PartoDTO partoDTO) {

        Optional<MaeModel> objMae;
        Optional<RecemNascidoModel> objRecemNascido;
        Optional<PartoModel> objParto;

        var recemNascido= new RecemNascidoModel();
        var mae = new MaeModel();


        objMae = maeRepository.findByCpf(partoDTO.getMae().getCpf());
        if(objMae.isPresent()) {
            objRecemNascido = recemNascidoRepository.findByRecemNascidoId(new RecemNascidoPK(objMae.get(), partoDTO.getDataParto()));
            if(objRecemNascido.isPresent()) {
                objParto = partoRepository.findByRecemNascido(objRecemNascido.get());
                if(objParto.isPresent()) throw new ResourceConflictPersistence("Parto Já cadastrado no sistema.");
            }
            recemNascido.setMaeModel(objMae.get());
        } else {
            System.out.println("Cadastrar parto.");

            mae.setCpf(partoDTO.getMae().getCpf());
            mae.setNome(partoDTO.getMae().getNome());
            mae.setDataNascimento(partoDTO.getMae().getDataNascimento());
            mae.setTelefone(partoDTO.getMae().getTelefone());
            mae.setEndereco(partoDTO.getMae().getEndereco());
            mae.setNumero(partoDTO.getMae().getNumero());
            mae.setComplemento(partoDTO.getMae().getComplemento());
            mae.setBairro(partoDTO.getMae().getBairro());
            mae.setUf(partoDTO.getMae().getUf());
            recemNascido.setMaeModel(mae);
        }

        recemNascido.setNome(partoDTO.getRecemNascido().getNome());
        recemNascido.setDataNascimento(partoDTO.getRecemNascido().getDataNascimento());
        recemNascido.setSexo(partoDTO.getRecemNascido().getSexo());
        recemNascido.setPeso(partoDTO.getRecemNascido().getPeso());
        recemNascido.setAltura(partoDTO.getRecemNascido().getAltura());
        recemNascido.setCondicao(partoDTO.getRecemNascido().getCondicao());
        recemNascido.setCpfPai(partoDTO.getRecemNascido().getCpfPai());



        // MaeModel mae = objMae.orElseThrow(() -> new ResourceNotFoundException("Mãe não encontrada no sistema."));


//        RecemNascidoModel recemNascido = objRecemNascido.orElseThrow(() -> new ResourceNotFoundException("Recém-nascido não encontrado no sistema."));




//        Optional<PartoModel> objParto = partoRepository.findByRecemNascido(recemNascido);
//        if(objParto.isPresent()) {
//            System.out.println("Partoooooooooo");
//            throw new ResourceConflictPersistence("Parto já cadastrado no sistema.");
//        }
        //RecemNascidoModel recemNascido = new RecemNascidoModel();




        PartoModel novoParto = new PartoModel();
        novoParto.setPartoRisco(partoDTO.getPartoRisco());
        novoParto.setTipoParto(partoDTO.getTipoParto());
        novoParto.setMultifetal(partoDTO.getMultifetal());
        novoParto.setObservacao(partoDTO.getObservacao());
        novoParto.setRecemNascido(recemNascido);

        Optional<EquipePartoModel> objEquipeParto = equipePartoRepository.findById(partoDTO.getEquipeParto().getEquipePartoId());
        var equipeParto = objEquipeParto.orElseThrow(() -> new ResourceNotFoundException("Equipe de parto não encontrada no sistema."));
        novoParto.setEquipeParto(equipeParto);
        System.out.println("------- Mae -------");
        System.out.println("Nome: " + mae.getNome());
        System.out.println("Cpf: " + mae.getCpf());
        System.out.println("Data Nascimento: " + mae.getDataNascimento());
        System.out.println("Telefone: " + mae.getTelefone());
        System.out.println("Endereço: " + mae.getEndereco());
        System.out.println("Número: " + mae.getNumero());
        System.out.println("Complemento: " + mae.getComplemento());
        System.out.println("Bairro: " + mae.getBairro());
        System.out.println("UF: " + mae.getUf());
        System.out.println("------- Recém-nascido -------");
        System.out.println("Nome: " + recemNascido.getNome());
        System.out.println("Data Nascimento: " + recemNascido.getDataNascimento());
        System.out.println("Sexo: " + recemNascido.getSexo());
        System.out.println("Peso: " + recemNascido.getPeso());
        System.out.println("Altura: " + recemNascido.getAltura());
        System.out.println("Condição: " + recemNascido.getCondicao());
        System.out.println("CPF Pai: " + recemNascido.getCpfPai());
        System.out.println("CPF Mãe: " + recemNascido.getMaeModel().getCpf());
        System.out.println("Nome da mãe: " + recemNascido.getMaeModel().getNome());
        System.out.println("------- `Equipe Parto -------");
        System.out.println("Id: " + equipeParto.getEquipePartoId());
        System.out.println("Doula: " + equipeParto.getDoula());
        System.out.println("\t------- Equipe Médica -------");
        for(EquipeMedicaModel em: equipeParto.getEquipeMedica()) {
            System.out.println("\tCRM: " + em.getMedico().getCrm());
            System.out.println("\tNome: " + em.getMedico().getNome());
            System.out.println("\tEspecialidade: " + em.getMedico().getEspecialidade().getNome());
            System.out.println("");
        }
        for(EquipeEnfermagemModel ef: equipeParto.getEquipeEnfermagemModel()) {
            System.out.println("\tCOREN: " + ef.getEnfermeiro().getInscricaoCoren());
            System.out.println("\tNome: " + ef.getEnfermeiro().getNome());
            System.out.println("");
        }

        System.out.println("Vai salvar");
        return new PartoDTO(partoRepository.save(novoParto));
    }
}
