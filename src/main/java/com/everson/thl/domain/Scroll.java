package com.everson.thl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "scroll")
public class Scroll {


    //TODO remover id ?
    @Id
    private String id;

    private Integer scrollNumber;

    private String longOption;
    private Double longStrike;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date longDate;
    private Double longPrice;
    private Integer longQuantity;
    private Date longExpireDateOption;

    private String shortOption;
    private Double shortStrike;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date shortDate;
    private Double shortPrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date shortExpireDateOption;
    private Integer shortQuantity;

    private Hedge hedge;

}
