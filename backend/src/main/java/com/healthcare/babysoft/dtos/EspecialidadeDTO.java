package com.healthcare.babysoft.dtos;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EspecialidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer especialidadeId;

    @NotBlank
    private String nome;

}
