package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import com.qa.model.Account;

public class AccountRepoDB implements AccountRepo {
	@PersistenceUnit(unitName="myPU")
	private EntityManagerFactory emf;

	public Account createAccount(Account account) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(account);
		et.commit();
		return account;
	}

	public Account getAccount(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Account.class, id);
	}

	public List<Account> getAccount() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Account> q = em.createQuery("Select acc from Account acc",
				Account.class);
		List<Account> accounts = q.getResultList();
		for (Account account : accounts) {
			System.out.println(account.getFirstName());
		}
		return accounts;
	}

	public Account updateName(int id, String newName) {
		Account foundAccount = retrieve(id);
		foundAccount.setFirstName(newName);
		return foundAccount;
		
	}

	public void deleteAccount(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(retrieve(id));
		et.commit();
	}

	public Account retrieve(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Account.class, id);
		
	}

}
