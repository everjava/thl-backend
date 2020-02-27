package com.everson.thl.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "strategy")
public class Strategy {

    @Id
    private String id;
   @NotBlank(message = "Nome da estratégia é obrigatório")
    private String name;

    private String optionType;

   //NotBlank(message = "Opção de compra é obrigatório")
    private String longOption;

   //NotBlank(message = "Strike de compra é obrigatório")
    private Double longStrike;

   //NotBlank(message = "Data da compra é obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date longDate;

   //NotBlank(message = "Preço de compra é obrigatório")
    private Double longPrice;

   //NotBlank(message = "Quantidade de compra é obrigatório")
    private Integer longQuantity;

    private Date longExpireDateOption;

   //NotBlank(message = "Opção de compra é obrigatório")
    private String shortOption;

   //NotBlank(message = "Strike de venda é obrigatório")
    private Double shortStrike;

    @JsonFormat(pattern = "dd/MM/yyyy")
   //NotBlank(message = "Data da venda é obrigatório")
    private Date shortDate;

   //NotBlank(message = "Preço de venda é obrigatório")
    private Double shortPrice;

   //NotBlank(message = "Quantidade de venda é obrigatório")
    private Integer shortQuantity;

    private Date shortExpireDateOption;

    private Double total;

    private List<Scroll> scrollList;

}
