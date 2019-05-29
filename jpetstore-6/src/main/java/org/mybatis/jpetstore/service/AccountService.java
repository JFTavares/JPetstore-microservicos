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
import org.mybatis.jpetstore.mapper.AccountMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * The Class AccountService.
 *
 * @author Eduardo Macarron
 */
@Service
public class AccountService implements IAccountService {

  private final AccountMapper accountMapper;
  private final RestTemplate rest;

  public AccountService(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
    this.rest = new RestTemplate();
    rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
  }

  @Override
  public Account getAccount(String username) {
    ResponseEntity<Account> response = rest.getForEntity("http://localhost:8081/account/" + username, Account.class);
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      return null;
    }
    return response.getBody();
  }

  @Override
  public Account getAccount(String username, String password) {
    System.out.println("buscando usuario " + username + " com senha " + password);
    ResponseEntity<Account> response = rest.getForEntity("http://localhost:8081/account/" + username + "/" + password,
        Account.class);
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      return null;
    }
    return response.getBody();
  }

  /**
   * Insert account.
   *
   * @param account
   *          the account
   */
  @Override
  @Transactional
  public void insertAccount(Account account) {
    accountMapper.insertAccount(account);
    accountMapper.insertProfile(account);
    accountMapper.insertSignon(account);
  }

  /**
   * Update account.
   *
   * @param account
   *          the account
   */
  public void updateAccount(Account account) {

    HttpEntity<Account> entity = new HttpEntity<>(account);

    ResponseEntity<Account> response = rest.exchange("http://localhost:8081/account/" + account.getUsername(),
        HttpMethod.PUT, entity, Account.class);

    if (response.getStatusCode() != HttpStatus.OK) {
      throw new RuntimeException("Deu erro na chamada de update");
    }
  }

}
