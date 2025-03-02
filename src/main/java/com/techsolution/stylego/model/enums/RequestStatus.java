package com.techsolution.stylego.model.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    IN_PROGRESS("IN PROGRESS", "EM ANDAMENTO"),
    FINISHED("FINISHED", "FINALIZADO"),
    CANCELED("CANCELED", "CANCELADO");

    private final String type;
    private final String value;

    RequestStatus(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static String getValueByType(String type) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getType().equalsIgnoreCase(type)) {
                return status.getValue();
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + type);
    }

    public static String getType(String type) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getType().equalsIgnoreCase(type)) {
                return status.getType();
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + type);
    }
}

