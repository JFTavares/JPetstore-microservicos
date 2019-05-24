package org.booknando.jpetstore.catalog;

import org.booknando.jpetstore.catalog.domain.Account;
import org.booknando.jpetstore.catalog.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CatalogRest {

    @Autowired
    private ICatalogService service;

    @RequestMapping(path = "/catalog/{username}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("username") String username) {
        System.out.println(username);
        Account account = service.getAccount(username);
        if (account == null) {
            throw new ResourceNotFoundException();
        }
        return account;
    }

    @RequestMapping(path = "/catalog/{username}/{password}")
    public Account getAccountPassword(@PathVariable("username") String username, @PathVariable("username") String password) {
        System.out.println(username + " - "+ password);
        Account account = service.getAccount(username, password);
        if (account == null) {
            throw new ResourceNotFoundException();
        }
        return account;
    }

    @RequestMapping(path = "/catalog/{username}", method = RequestMethod.PUT)
    public Account update(@RequestBody Account account) {
        service.updateAccount(account);

        return service.getAccount(account.getUsername());
    }
}
