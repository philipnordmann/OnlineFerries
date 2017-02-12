package de.onlineferries.model.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.onlineferries.entity.Customer;
import de.onlineferries.view.CustomerView;

public class LoginServiceImpl implements LoginService {

	@Override
	public CustomerView login(String username, String password) {

		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		CustomerView customerView = null;

		try {
			Query q = em.createNamedQuery("loginCustomer", Customer.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			Customer customer = (Customer) q.getSingleResult();
			customerView = new CustomerView(customer.getId(), customer.getFirstname(), customer.getName(),
					customer.getPassword(), customer.getStreet(), customer.getZipcode(), customer.getCity(),
					customer.getEmail());
		} catch (NoResultException ex) {
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}

		return customerView;
	}

	public void changeCustomer(CustomerView cust) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			Customer customer = em.find(Customer.class, cust.getCustomer_id());
			customer.setFirstname(cust.getFirstname());
			customer.setName(cust.getLastname());
			customer.setStreet(cust.getStreet());
			customer.setCity(cust.getCity());
			customer.setZipcode(cust.getZipcode());
			customer.setPassword(cust.getPassword());
			
			em.persist(customer);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}
	
	public void registerCustomer(CustomerView cust) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			Customer customer = new Customer();
			customer.setFirstname(cust.getFirstname());
			customer.setName(cust.getLastname());
			customer.setStreet(cust.getStreet());
			customer.setCity(cust.getCity());
			customer.setZipcode(cust.getZipcode());
			customer.setEmail(cust.getEmail());
			customer.setPassword(cust.getPassword());
			
			em.persist(customer);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

}
