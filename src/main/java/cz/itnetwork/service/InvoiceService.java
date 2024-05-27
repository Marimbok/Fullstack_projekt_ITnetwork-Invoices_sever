package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticDTO;

import java.util.List;

public interface InvoiceService {

    /**
     * Create new invoice with existing persons
     * @param invoiceDTO invoice to create
     * @return newly created invoice
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Remove invoice completely from database.
     * @param id ID of invoice to remove
     */
    void removeInvoice(long id);

    /**
     * Fetch all invoices by set filters. With no set filter, there will be shown 10 invoices on one page.
     * @param invoiceFilter Parameters of filter set by user.
     * @return List of invoices that get through filter
     */
    List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter);

    /**
     * Fetch invoice by ID.
     * @param id ID of wanted invoice.
     * @return Fetched invoice.
     */
    InvoiceDTO getInvoiceById(long id);

    /**
     * Method for editing invoices, where origin invoice will be overwritten.
     * @param invoiceId ID of invoice, which will be overwritten
     * @param invoiceDTO DTO of new invoice to that will be saved in database.
     * @return DTO of saved invoice.
     */
    InvoiceDTO editInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * Statistics of all invoices.
     * @return Current year SUM, all time SUM and number of invoices.
     */
    InvoiceStatisticDTO getInvoiceStatistics();

}
