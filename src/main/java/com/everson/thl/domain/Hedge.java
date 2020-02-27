package com.everson.thl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "hedge")
public class Hedge {

    @Id
    private String id;

    private String optionType;

    private String option;

    private Double strike;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private Double price;

    private Integer quantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expireDateOption;

}
