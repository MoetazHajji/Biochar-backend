package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AccountDtoTopic;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.*;
import tn.esprit.Mappers.AccountMapper;
import tn.esprit.Mappers.AppointmentMapper;
import tn.esprit.Mappers.UserMapper;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.exception.RessourceNotFoundException;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("Account")
public class AccountService  implements IAccountService {
    @Autowired
    private KafkaTemplate<Object, AccountDtoTopic> kafkaTemplateAccountDto;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private IAppointementService iAppointementService;
    private IFileService ifileService;
    private IAttachmentService iAttachmentService;
    @Autowired
    public AccountService(AccountRepository accountRepository ,
                          UserRepository userRepository,
                          IAppointementService iAppointementService ,
                          IFileService ifileService,
                          IAttachmentService iAttachmentService)
    {this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.iAppointementService = iAppointementService;
        this.ifileService = ifileService;
        this.iAttachmentService = iAttachmentService;
    }

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
    public  AccountDto  selectbyUsername(String  Usename) {
        Account account = accountRepository.findAccountsByUsername(Usename)  ;
        return AccountMapper.mapToDto(account);
    }
    @Override
    public AccountDto Insert(AccountDto object) {
        object.setCreatedAt(   LocalDateTime.now() );
        Account account = AccountMapper.mapToEntity(object);
        try {
        MultipartFile multipartFile = ifileService.importFileToMultipartFile(FileService.defaultUserPhoto);
        Attachment attachment  = iAttachmentService.saveAttachment(multipartFile);
        account.setAttachment(attachment);   }
        catch (IOException e) {   System.out.println("IOException: "+e.getMessage());  }
        catch (Exception e) {     System.out.println("Exception: "+e.getMessage());    }

        AccountDtoTopic accountDtoTopic = AccountMapper.mapToDtoTopic(account);
        kafkaTemplateAccountDto.  send("topic-service-user-account-insert",  accountDtoTopic  );
        kafkaTemplateAccountDto.flush();
        return  AccountMapper.mapToDto( accountRepository.save(account));
    }
    @Override
    @Transactional
    public  AccountDto  update(AccountDto object) {
        Account account = accountRepository.findById(object.getId()).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : update Account not existe with id : "+object.getId()))  ;
        Account accountUpdate = AccountMapper.mapToEntity(object);
        account.setFirstname(accountUpdate.getFirstname());
        account.setLastname(accountUpdate.getLastname());
        account.setCin(accountUpdate.getCin());
        account.setDateOfBirth(accountUpdate.getDateOfBirth());
        account.setHireDate(accountUpdate.getHireDate());
        account.setPhone(accountUpdate.getPhone());
        account.setEmail(accountUpdate.getEmail());
        account.setGender(accountUpdate.getGender());
        account.setState(accountUpdate.getState());
        account.setCity(accountUpdate.getCity());
        account.setZip_code(accountUpdate.getZip_code());
        account.setAdresse(accountUpdate.getAdresse());
        account.setTeam(accountUpdate.getTeam());
        account.setShift(accountUpdate.getShift());
        account = accountRepository.save(account);
        AccountDtoTopic accountDtoTopic = AccountMapper.mapToDtoTopic(account);
        kafkaTemplateAccountDto.  send("topic-service-user-account-update",  accountDtoTopic  );
        kafkaTemplateAccountDto.flush();
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
        AccountDtoTopic accountDtoTopic = AccountMapper.mapToDtoTopic(account);
        kafkaTemplateAccountDto.  send("topic-service-user-account-delete",  accountDtoTopic  );
        kafkaTemplateAccountDto.flush();
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




