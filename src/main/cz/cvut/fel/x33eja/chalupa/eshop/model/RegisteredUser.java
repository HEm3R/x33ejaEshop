package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "registered_users")
public class RegisteredUser extends User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5163041702683292385L;
	private Address billingAddress;
	private Address deliveryAddress;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<Order> orders = new HashSet<Order>(0);

	public RegisteredUser() {
	}

	public RegisteredUser(Address addressesByDeliveryAddress) {
		this.deliveryAddress = addressesByDeliveryAddress;
	}

	public RegisteredUser(Address addressesByBillingAddress,
			Address addressesByDeliveryAddress, Set<Order> orderses) {
		this.billingAddress = addressesByBillingAddress;
		this.deliveryAddress = addressesByDeliveryAddress;
		this.orders = orderses;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_address")
	public Address getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_address", nullable = false)
	@NotNull
	public Address getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "registered_user_product", joinColumns = { @JoinColumn(name = "registered_user") }, inverseJoinColumns = { @JoinColumn(name = "product") })
	public Set<Product> getRoles() {
		return products;
	}

	public void setRoles(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "registeredUser")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
