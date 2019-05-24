package org.booknando.jpetstore.account.service;

import org.booknando.jpetstore.account.domain.Account;

public interface IAccountService {

	Account getAccount(String username);

	Account getAccount(String username, String password);

	/**
	   * Insert account.
	   *
	   * @param account
	   *          the account
	   */
	void insertAccount(Account account);

	/**
	   * Update account.
	   *
	   * @param account
	   *          the account
	   */
	void updateAccount(Account account);

}