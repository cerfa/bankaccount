package fenn.bank.account.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity annotation specifies that the class is mapped to a database table.
@Entity
@Table(name="CUSTOMERS_ACCOUNT",schema = "DBACC")
public class Account {

	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	private String customerId;
	private BigDecimal credit;
	private Timestamp timeStamp;


	public Account() {
	}
	public Account(String customerId, BigDecimal credit, Timestamp timeStamp) {
		this.customerId = customerId;
		this.credit = credit;
		this.timeStamp = timeStamp;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}