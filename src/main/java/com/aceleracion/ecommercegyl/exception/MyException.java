package com.aceleracion.ecommercegyl.exception;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {

    private final String code;
    private final String field;
    private HttpStatus httpStatus;

    public MyException(String code) {
        this.code = code;
        this.field = "";
    }

    public MyException(String code, String field) {
        this.code = code;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        switch (code) {
            case "existDB" -> {
                httpStatus = HttpStatus.CONFLICT;
                return "El recurso: " + field + " que desea crear ya existe";
            }
            case "entityNull" -> {
                httpStatus = HttpStatus.BAD_REQUEST;
                return "El recurso: " + field + " que ingresó es nulo";
            }
            case "existDBStatus" -> {
                httpStatus = HttpStatus.GONE;
                return "El recurso: " + field + " existe pero esta dado de baja";
            }
            case "noExistDB" -> {
                httpStatus = HttpStatus.NOT_FOUND;
                return "El recurso: " + field + " no existe en la base de datos";
            }
            case "noDataFound" -> {
                httpStatus = HttpStatus.NOT_FOUND;
                return "No se encontraron resultados";
            }
            case "ExistBranchAddress" -> {
                httpStatus = HttpStatus.CONFLICT;
                return "Ya existe una sucursal en esta dirección";
            }
            case "NotValid" -> {
                return "El recurso: " + field + " es inválido";
            }
            default -> {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                return "No se reconoce el código de error";
            }
        }
    }
}

