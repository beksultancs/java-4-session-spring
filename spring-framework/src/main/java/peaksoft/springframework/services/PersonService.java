package peaksoft.springframework.services;

import org.springframework.stereotype.Service;
import peaksoft.springframework.exceptions.BadRequestException;
import peaksoft.springframework.exceptions.NotFoundException;
import peaksoft.springframework.models.Passport;
import peaksoft.springframework.models.Person;
import peaksoft.springframework.repositories.PersonRepo;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Beksultan
 */
@Service
public class PersonService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Person findById(Long personId) {
        return getPerson(personId);
    }

    private Person getPerson(Long personId) {
        return personRepo.findById(personId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("person with id = %d does not exists", personId)
                ));
    }

    public Person save(Person person) {
        String email = person.getEmail();

        boolean exists = personRepo.existsByEmail(email);

        if (exists) {
            throw new BadRequestException(
                    String.format("person with email = %s has already exists", email)
            );
        }

        return personRepo.save(person);
    }

    @Transactional
    public Person update(Long personId, Person newPerson) {
        Person person = getPerson(personId);

        String currentName = person.getName();
        String newName = newPerson.getName();

        if (!Objects.equals(currentName, newName)) {
            person.setName(newName);
        }

        String currentEmail = person.getEmail();
        String newEmail = newPerson.getEmail();

        if (!Objects.equals(currentEmail, newEmail)) {
            person.setEmail(newEmail);
        }

        int currentAge = person.getAge();
        int newAge = newPerson.getAge();

        if (currentAge != newAge) {
            person.setAge(newAge);
        }

        Passport currentPassport = person.getPassport();
        Passport newPassport = newPerson.getPassport();

        if (!Objects.equals(currentPassport, newPassport)) {
            person.setPassport(newPassport);
        }

        return person;
    }

    public void deleteById(Long personId) {
        boolean exists = personRepo.existsById(personId);

        if (!exists) {
            throw new BadRequestException(
                    String.format("person with id = %d does not exists", personId)
            );
        }

        personRepo.deleteById(personId);
    }

    public Person findByPassportNumber(String passportNumber) {
        return personRepo.findByPassportNumber(passportNumber)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Person with passport number %s does not exists", passportNumber)
                ));
    }
}
