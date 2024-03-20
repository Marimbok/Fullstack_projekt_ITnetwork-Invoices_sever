package cz.itnetwork.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int invoiceNumber;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issued;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @Column(nullable = false)
    private String product;

    private long price;

    private int vat;

    private String note;


    @ManyToOne
    private PersonEntity buyer;

    @ManyToOne
    private PersonEntity seller;

}
