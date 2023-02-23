package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.Gender;
import tn.esprit.Entitys.User;
import tn.esprit.Mappers.AccountMapper;
import tn.esprit.Mappers.UserMapper;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.UserRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("Account")
public class AccountService  implements IAccountService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository ,UserRepository userRepository ){this.accountRepository = accountRepository;this.userRepository = userRepository;}

    @Override
    public List<AccountDto> SelectAll() {
        return accountRepository.findAll().stream().map(account -> AccountMapper.mapToDto(account)).collect(Collectors.toList());   }

    @Override
    public  ResponseEntity<AccountDto>  SelectBy(long id) {
        Account account = accountRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(AccountMapper.mapToDto(account));
    }
    @Override
    public AccountDto Insert(AccountDto object) {
        object.setDateCreation( new Date(System.currentTimeMillis()));
        Account account = AccountMapper.mapToEntity(object);
        return  AccountMapper.mapToDto( accountRepository.save(account));
    }
    @Override
    @Transactional
    public ResponseEntity<AccountDto> update(AccountDto object) {
        Account account = accountRepository.findById(object.getId()).orElse(null)  ;

        account = accountRepository.save(AccountMapper.mapToEntity(object));
        return ResponseEntity.ok(AccountMapper.mapToDto(account));
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        Account account = accountRepository.findById(id).orElse(null) ;
        accountRepository.delete(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }

    @Override
    @Transactional
    public AccountDto  assignUserToAccount(Long idUser, Long idAccount)
    {
        User user = userRepository.findById(idUser).orElse(null);
        Account account = accountRepository.findById(idAccount).orElse(null);
        account.setUser(user);
        return AccountMapper.mapToDto( accountRepository.save(account) ) ;
    }

}




