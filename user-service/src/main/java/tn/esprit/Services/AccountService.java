package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.Gender;
import tn.esprit.Entitys.User;
import tn.esprit.Repositorys.AccountRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Service("Account")
public class AccountService  implements IGenericCRUD<Account> {

    private AccountRepository accountRepository;
    @Autowired // Methode 2
    public AccountService(AccountRepository accountRepository){this.accountRepository = accountRepository;}


    @Override
    public List<Account> SelectAll() {  return accountRepository.findAll();  }

    @Override
    public  ResponseEntity<Account>  SelectBy(long id) {
        Account account = accountRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(account);
    }

    @Override
    public Account Insert(Account object) {
        return accountRepository.save(object);
    }

    @Override
    @Transactional
    public ResponseEntity<Account> update(Account object) {
        Account account = accountRepository.findById(object.getId()).orElse(null)  ;
        accountRepository.save(account);
        return ResponseEntity.ok(account);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        Account account = accountRepository.findById(id).orElse(null) ;
        accountRepository.delete(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




