package fenn.bank.account.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
// @Entity annotation specifies that the class is mapped to a database table.
@Entity
@Table(name="CUSTOMERS",schema = "DBACC")
public class Customer {
 
    // @Id annotation specifies the primary key of an entity.
    // @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
    private String customerId;
    private String name;
    private String surname;
 
    // Default constructor.
    public Customer() {  }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Customer(String customerId, String name, String surname) {
		this.customerId = customerId;
		this.name = name;
		this.surname = surname;
	}
 
}