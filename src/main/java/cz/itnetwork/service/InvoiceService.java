package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    void removeInvoice(long id);

    List<InvoiceDTO> getAll();
    InvoiceDTO getInvoiceById(long id);

}
