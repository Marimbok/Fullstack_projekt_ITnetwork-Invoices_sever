package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private PersonService personService;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceEntity.setSeller(personService.fetchPersonById(invoiceDTO.getSeller().getId()));
        invoiceEntity.setBuyer(personService.fetchPersonById(invoiceDTO.getBuyer().getId()));
        invoiceEntity = invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public void removeInvoice(long invoiceId) {
            invoiceRepository.delete(fetchInvoiceById(invoiceId));
    }

    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(id);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public InvoiceDTO editInvoice(long invoiceId, InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);

        invoiceEntity.setBuyer(personService.fetchPersonById(invoiceDTO.getBuyer().getId()));
        invoiceEntity.setSeller(personService.fetchPersonById(invoiceDTO.getSeller().getId()));

        invoiceMapper.updateInvoiceEntity(invoiceDTO, invoiceEntity);

        invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public InvoiceStatisticDTO getInvoiceStatistics() {
        return invoiceRepository.getInvoiceStatistics();
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
