package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.entity.PersonEntity;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    PersonDTO getPersonById(long id);

    PersonDTO editPerson(long personId, PersonDTO personDTO);

    List<InvoiceDTO> invoicesBySeller(String personIdNum);

    List<InvoiceDTO> invoicesByBuyer(String personIdNum);

    PersonEntity fetchPersonById(long id);

    List<PersonStatisticDTO> getPersonsStatistics();
}
