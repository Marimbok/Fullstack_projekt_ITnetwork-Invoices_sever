/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping({"/persons", "/persons/"})
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return  personService.addPerson(personDTO);
    }

    @GetMapping({"/persons", "/persons/"})
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    @DeleteMapping({"/persons/{personId}", "/persons/{personId}"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    @GetMapping({"/persons/{personId}", "/persons/{personId}/"})
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPersonById(personId);
    }

    @PutMapping({"/persons/{personId}", "/persons/{personId}/"})
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
        return personService.editPerson(personId, personDTO);
    }

    @GetMapping({"/identification/{personIdNum}/sales", "/identification/{personIdNum}/sales/"})
    public List<InvoiceDTO> getPersonsSales(@PathVariable String personIdNum) {
        return personService.invoicesBySeller(personIdNum);
    }

    @GetMapping({"identification/{personIdNum}/purchases", "identification/{personIdNum}/purchases/"})
    public List<InvoiceDTO> getPersonsPurchases(@PathVariable String personIdNum) {
        return personService.invoicesByBuyer(personIdNum);
    }

    @GetMapping({"/persons/statistics/", "/persons/statistics"})
    public List<PersonStatisticDTO> getPersonsStatistic(){
        return personService.getPersonsStatistics();
    }
}

