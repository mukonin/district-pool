package entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.persistence.*;

import utils.*;

/**
 * @ author Mukonin Oleksandr
 *
 */
@XmlRootElement(name="person")
@XmlType(propOrder = {"id", "lastName", "firstName", "date"})
@JsonPropertyOrder({ "id", "lastName", "firstName", "date"})
@Entity
@Table(name="users")
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private DateTime date;
    private long id;

    @Id
    @Column(name="id") // not necessary
    @XmlAttribute(name = "id")
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonSerialize(using = DateJsonAdapter.class)
    @XmlJavaTypeAdapter(DateXmlAdapter.class)
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

    @JsonDeserialize(using = JsonDateAdapter.class)
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
        return lastName + " " + firstName + " (" + date.toString("dd.MM.yyyy") + ")";
    }
}
