package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.User;
import tn.esprit.exception.Mappers.AccountMapper;
import tn.esprit.exception.Mappers.AppointmentMapper;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.exception.RessourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service("Account")
public class AccountService  implements IAccountService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private IAppointementService iAppointementService;

    @Autowired
    public AccountService(AccountRepository accountRepository ,
                          UserRepository userRepository,
                          IAppointementService iAppointementService)
    {this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.iAppointementService = iAppointementService;}

    @Override
    public List<AccountDto> SelectAll() {
        List<Account> ListAcoount = accountRepository.findAll();
        return ListAcoount.stream().map(account -> AccountMapper.mapToDto(account)) .collect(Collectors.toList());
    }

    @Override
    public  AccountDto  SelectBy(long id) {
        Account account = accountRepository.findById(id).orElse(null)  ;
        return AccountMapper.mapToDto(account);
    }
    @Override
    public AccountDto Insert(AccountDto object) {
        object.setCreatedAt(   LocalDateTime.now() );
        Account account = AccountMapper.mapToEntity(object);
        return  AccountMapper.mapToDto( accountRepository.save(account));
    }
    @Override
    @Transactional
    public  AccountDto  update(AccountDto object) {
        Account account = accountRepository.findById(object.getId()).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : update Account not existe with id : "+object.getId()))  ;
        //account.setEmail(object.getEmail());
        account = accountRepository.save(AccountMapper.mapToEntity(object));
        return AccountMapper.mapToDto(account) ;
    }

    @Override
    public  boolean delete(long id) {
        boolean deleted = false;
        Account account = accountRepository.findById(id) .
                orElseThrow(()-> new RessourceNotFoundException("Service Account : delete Account not existe with id : "+id)) ;
        if (account != null ) {
            accountRepository.delete(account);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }

    @Override
    @Transactional
    public AccountDto  assignUserToAccount(Long idUser, Long idAccount)
    {
        User user = userRepository.findById(idUser).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : assignUserToAccount User not existe with id : "+idUser)) ;
        Account account = accountRepository.findById(idAccount).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : assignUserToAccount Account not existe with id : "+idUser)) ;

        account.setUser(user);
        return AccountMapper.mapToDto( accountRepository.save(account) ) ;
    }
    @Transactional
    public AccountDto  AddAppointementAndToAccount(  Long idAccount , AppointmentDto object )
    {
        Account account = accountRepository.findById(idAccount).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : AddAppointementAndToAccount Account not existe with id : "+idAccount)) ;

        AppointmentDto appointmentDto = iAppointementService.Insert(object);
        account.getAppointments().add(AppointmentMapper.mapToEntity( appointmentDto ));
        return AccountMapper.mapToDto( accountRepository.save(account) ) ;
    }
}




