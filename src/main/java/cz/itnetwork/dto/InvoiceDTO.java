package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.entity.PersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDTO {

    @JsonProperty("_id")
    private Long id;

    private int invoiceNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issued;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private String product;

    private long price;

    private int vat;

    private String note;

    private PersonDTO buyer;

    private PersonDTO seller;
}
