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

    /**
     * Edit existing person. After editing method will set origin person as hidden and create new edit person.
     * @param personId ID of origin person, that user want edit.
     * @param personDTO DTO of edited person send by user without ID.
     * @return DTO of edited person with new ID.
     */
    PersonDTO editPerson(long personId, PersonDTO personDTO);

    /**
     * Fetch all invoices with same ID number of seller.
     * @param personIdNum ID number of seller, which invoices we want get.
     * @return List of all invoices with same seller.
     */
    List<InvoiceDTO> invoicesBySeller(String personIdNum);

    /**
     * Fetch all invoices with same ID number of buyer.
     * @param personIdNum ID number of buyer, which invoices we want get.
     * @return List of all invoices with same buyer.
     */
    List<InvoiceDTO> invoicesByBuyer(String personIdNum);

    /**
     * Fetch person by ID.
     * @param id ID of wanted person.
     * @return Entity of fetched person.
     */
    PersonEntity fetchPersonById(long id);

    /**
     * SUM of persons sales.
     * @return List of people with individual IDs, names and revenue.
     */
    List<PersonStatisticDTO> getPersonsStatistics();
}
