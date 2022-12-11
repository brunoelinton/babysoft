package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.RecemNascidoDTO;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.repositories.RecemNascidoRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class RecemNascidoService {

    @Autowired
    private RecemNascidoRepository recemNascidoRepository;

    @Autowired
    private MaeRepository maeRepository;

    @Transactional(readOnly = true)
    public Page<RecemNascidoDTO> buscarTodosRecemNascidos(Pageable pageable) {
        Page<RecemNascidoModel> lista = recemNascidoRepository.findAll(pageable);
        return lista.map((recemNascido) -> new RecemNascidoDTO(recemNascido));
    }

    @Transactional(readOnly = true)
    public RecemNascidoDTO buscarUmRecemNascido(String recemNascidoId) {
        try {
            String cpfMae = recemNascidoId.substring(0, 11);
            String dataNascimentoStr = recemNascidoId.substring(11);
            StringBuilder sbDataNascimentoBr = new StringBuilder(dataNascimentoStr);
            sbDataNascimentoBr.insert(2, '-');
            sbDataNascimentoBr.insert(5, '-');
            sbDataNascimentoBr.insert(10, ' ');
            sbDataNascimentoBr.insert(13, ':');
            sbDataNascimentoBr.insert(16, ':');
            SimpleDateFormat sdfBR = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateTime = sdfBR.parse(sbDataNascimentoBr.toString());
            LocalDateTime ldt = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());

            Optional<MaeModel> maeObj = maeRepository.findByCpf(cpfMae);
            MaeModel mae = maeObj.orElseThrow(() -> new ResourceNotFoundException("Não há recém-nascido com o id informado."));
            Optional<RecemNascidoModel> obj = recemNascidoRepository.findByRecemNascidoId(new RecemNascidoPK(mae, ldt));
            RecemNascidoModel recemNascidoModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há recém-nascido com o id informado."));
            return new RecemNascidoDTO(recemNascidoModel);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Recém-nascido não encontrado no sistema");
        }
    }

    @Transactional
    public RecemNascidoDTO cadastrarRecemNascido(RecemNascidoDTO recemNascidoDTO) {
        Optional<MaeModel> objMae = maeRepository.findByCpf(recemNascidoDTO.getCpfMae());
        MaeModel maeModel = objMae.orElseThrow(() -> new ResourceNotFoundException("Não há mãe com o CPF informado."));
        var recemNascido = new RecemNascidoModel();
        copiarDTOParaEntidade(recemNascidoDTO, recemNascido);
        recemNascido.setMaeModel(maeModel);
        Optional<RecemNascidoModel> objRecemNascido = recemNascidoRepository.findByRecemNascidoId(recemNascido.getRecemNascidoId());
        if(objRecemNascido.isPresent()) {
            throw new ResourceConflictPersistence("Recém-nascido já cadastrado no sistema.");
        }
        recemNascido = recemNascidoRepository.save(recemNascido);
        return new RecemNascidoDTO(recemNascido);
    }

    private void copiarDTOParaEntidade(RecemNascidoDTO recemNascidoDTO, RecemNascidoModel recemNascidoModel) {
        recemNascidoModel.setNome(recemNascidoDTO.getNome());
        recemNascidoModel.setDataNascimento(recemNascidoDTO.getDataNascimento());
        recemNascidoModel.setSexo(recemNascidoDTO.getSexo());
        recemNascidoModel.setPeso(recemNascidoDTO.getPeso());
        recemNascidoModel.setAltura(recemNascidoDTO.getAltura());
        recemNascidoModel.setCondicao(recemNascidoDTO.getCondicao());
        recemNascidoModel.setCpfPai(recemNascidoDTO.getCpfPai());
    }

}

