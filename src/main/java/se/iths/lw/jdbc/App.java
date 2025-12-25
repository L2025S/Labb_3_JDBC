package se.iths.lw.jdbc;

import java.util.List;

public class App {
    static void main(String[] args) {
        PersonDAO dao = new PersonDAOImpl();
        PersonService service = new PersonService(dao);

        Person p = new Person();
        p.setFirstName("Anna");
        p.setLastName ("Andersson");
        p.setDob(java.sql.Date.valueOf("1990-05-01"));
        p.setIncome(55000);

        service.addPerson(p);

        List<Person> rich = service.getPersonsWithIncomeAbove(50000);
        System.out.println("Personer med hög inkomst: " + rich.size());
    }
}
