package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    public PersonController() {
    }

    /*@PostMapping
    public ResponseEntity<Person> createPerson(@RequestParam("person") Person person){
        return new ResponseEntity<>(this.personRepository.save(person), HttpStatus.CREATED);
    }*/


    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person person1 = new Person(person.getFirstName(), person.getLastName(), (int) person.getId());

        return new ResponseEntity<>(this.personRepository.save(person1), HttpStatus.CREATED);
       // return new ResponseEntity<>(this.personRepository.save(person), HttpStatus.CREATED);
    }

   /* public ResponseEntity<Muffin> create(@RequestParam("muffin") Muffin muffin) {
        return new ResponseEntity<>(this.muffinRepository.save(muffin), HttpStatus.CREATED);
    }
*/

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person person = this.personRepository.findOne(id);
        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(this.personRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person){
        Person person1 = this.personRepository.findOne(id);

            person1.setFirstName(person.getFirstName());
            person1.setLastName(person.getLastName());
            return new ResponseEntity<>(person1, HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id){
        this.personRepository.delete(personRepository.findOne(id));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
