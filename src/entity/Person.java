package entity;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @ author Mukonin Oleksandr
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="role")
@Table(name="hospital")
public class Person implements Comparable<Person> {
	
    private String firstName;
    private String lastName;
    private DateTime date;
    private long id;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Column(columnDefinition = "date")
    public DateTime getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public int compareTo(Person person) {
        if ((lastName.compareToIgnoreCase(person.getLastName()) == 0)&& firstName.compareToIgnoreCase(person.getFirstName()) == 0) {
            return Long.compare(date.getMillis(), person.date.getMillis());
        } else if (lastName.compareToIgnoreCase(person.getLastName()) == 0) {
            firstName.compareToIgnoreCase(person.getFirstName());
        }
        return lastName.compareToIgnoreCase(person.getLastName());
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
	
}
