package peaksoft.springframework.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springframework.models.Person;
import peaksoft.springframework.services.PersonService;

import java.util.List;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/people")
@AllArgsConstructor
public class PersonApi {

    private final PersonService personService;

    // find all
    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    // find by id
    @GetMapping("/find/{personId}")
    public Person findById(@PathVariable Long personId) {
        return personService.findById(personId);
    }

    // save
    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    // update
    @PutMapping("/update/{personId}")
    public Person update(@PathVariable Long personId,
                         @RequestBody Person person) {
        return personService.update(personId, person);
    }

    // delete by id
    @DeleteMapping("/delete/{personId}")
    public void delete(@PathVariable Long personId) {
        personService.deleteById(personId);
    }

    // find by PassportNumber
    @GetMapping("/find/by/{passportNumber}")
    public Person findByPassportNumber(@PathVariable String passportNumber) {
        return personService.findByPassportNumber(passportNumber);
    }

}
