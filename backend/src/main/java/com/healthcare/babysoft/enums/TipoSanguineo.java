package com.healthcare.babysoft.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum TipoSanguineo {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private String tipoSanguineo;
    TipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    @Converter(autoApply = true)
    public static class Mapeador implements AttributeConverter<TipoSanguineo, String> {
        @Override
        public String convertToDatabaseColumn(TipoSanguineo tipoSanguineo) {
            return String.valueOf(tipoSanguineo.getTipoSanguineo());
        }

        @Override
        public TipoSanguineo convertToEntityAttribute(String y) {
            if(y == null) return null;
            if("A+".equals(y)) return A_POSITIVO;
            if("A-".equals(y)) return A_NEGATIVO;
            if("B+".equals(y)) return B_POSITIVO;
            if("B-".equals(y)) return B_NEGATIVO;
            if("AB+".equals(y)) return AB_POSITIVO;
            if("AB-".equals(y)) return AB_NEGATIVO;
            if("O+".equals(y)) return O_POSITIVO;
            if("O-".equals(y)) return O_NEGATIVO;
            throw new IllegalStateException("Valor inválido para tipo saguíneo: " + y);
        }
    }
}
