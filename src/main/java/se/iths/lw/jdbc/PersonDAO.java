package se.iths.lw.jdbc;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    Person findById(Integer id);
    void insert (Person person);
    void update (Person person);
    void delete (Integer id);
}
