package Web.servies;

import Web.models.Person;
import Web.repositories.PeopleRepository;

import java.util.List;

public class PeopleServies {

    private final PeopleRepository peopleRepository;

    public PeopleServies(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public
}
