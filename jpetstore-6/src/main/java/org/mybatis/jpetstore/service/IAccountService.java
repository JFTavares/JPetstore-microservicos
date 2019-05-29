/**
 *    Copyright 2010-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Account;
import org.springframework.transaction.annotation.Transactional;

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