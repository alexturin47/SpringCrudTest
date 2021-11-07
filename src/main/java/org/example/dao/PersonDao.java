package org.example.dao;

import org.example.models.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(PEOPLE_COUNT++, "Tom"));
        people.add(new Person(PEOPLE_COUNT++, "Jim"));
        people.add(new Person(PEOPLE_COUNT++, "Rob"));
        people.add(new Person(PEOPLE_COUNT++, "Tim"));
        people.add(new Person(PEOPLE_COUNT++, "Bob"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

}
