package com.healthcare.babysoft.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FactoryId {

    public static String buildId(LocalDateTime dataNascimento) {
        SimpleDateFormat sdfBR = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateTime = Date.from(dataNascimento.atZone(ZoneId.systemDefault()).toInstant());
        String dataId = sdfBR.format(dateTime);
        dataId = dataId.replaceAll("-", "");
        dataId = dataId.replaceAll(" ", "");
        dataId = dataId.replaceAll(":", "");
        return dataId;
    }

}
