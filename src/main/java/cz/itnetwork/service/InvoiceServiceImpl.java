package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceEntity.setSeller(fetchPersonById(invoiceDTO.getSeller().getId()));
        invoiceEntity.setBuyer(fetchPersonById(invoiceDTO.getBuyer().getId()));
        invoiceEntity = invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public void removeInvoice(long invoiceId) {
        try{
            InvoiceEntity invoice = fetchInvoiceById(invoiceId);
            invoiceRepository.save(invoice);
        } catch (NotFoundException ignored) {
        }

    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(id);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
}
