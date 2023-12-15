package com.aceleracion.ecommercegyl.dto.request;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class DateRequestDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha no debe ser nula")
    Date date;

    public DateRequestDTO(Date date) {
        this.date = date;
    }

    public DateRequestDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
