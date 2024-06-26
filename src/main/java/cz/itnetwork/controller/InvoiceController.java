package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping({"/invoices", "/invoices/"})
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping({"/invoices", "/invoices/"})
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter){
        return invoiceService.getAll(invoiceFilter);
    }

    @DeleteMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.removeInvoice(invoiceId);

    }

    @GetMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    public InvoiceDTO getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @PutMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO){
        invoiceDTO.setId(invoiceId);
        return invoiceService.editInvoice(invoiceId, invoiceDTO);
    }

    @GetMapping({"/invoices/statistics", "/invoices/statistics/"})
    public InvoiceStatisticDTO getInvoiceStatistics (){
        return invoiceService.getInvoiceStatistics();
    }
}
