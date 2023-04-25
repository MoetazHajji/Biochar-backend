package tn.esprit.AppRunner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.ExternelEntity.Account;
import tn.esprit.Entity.ExternelEntity.Roles;
import tn.esprit.Entity.ExternelEntity.Team;
import tn.esprit.Entity.Shift;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.Work_ScheduleRepository;

import java.util.HashSet;
import java.util.Set;

@Order(value = 1)
@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class bean_ResetData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean of Reset Data  run method Started !!" );
         this.reset( );
    }

    private final Work_ScheduleRepository workScheduleRepository ;

    private final AccountRepository accountRepository;
    private void reset(){

      // accountRepository.deleteAll();
      /* for ( Account account : accountSet)
        {
            accountRepository.save(account);
        }
*/
       //workScheduleRepository.deleteAll();
    }

    public Account Chief_Service = new Account(  "Chief_Service_1", 04256314, Roles.Chief_Service, Shift.Morning);
    public Account Doctor = new Account( "Doctor_1", 07142365,Roles.Doctor, Shift.Morning);
    public Account Receptionist1 = new Account("Receptionist_1", 32145896, Roles.Receptionist, Shift.Morning);
    public Account Receptionist2 = new Account("Receptionist_2", 04256317, Roles.Receptionist, Shift.Morning);
    public Account Receptionist3 = new Account("Receptionist_3", 54128967, Roles.Receptionist, Shift.Afternoon);
    public Account Receptionist4 = new Account("Receptionist_4", 24348967, Roles.Receptionist, Shift.Afternoon);
    public Account Receptionist5 = new Account("Receptionist_5", 36545896, Roles.Receptionist, Team.Team_A, Shift.Night) ;
    public Account Receptionist6 = new Account("Receptionist_6", 41145896, Roles.Receptionist, Team.Team_B, Shift.Night);
    public Account Biologist1 = new Account("Biologist_1", 34149896, Roles.Biologist, Shift.Morning);
    public Account Biologist2 = new Account("Biologist_2", 37145832, Roles.Biologist, Shift.Morning);
    public Account Biologist3 = new Account("Biologist_3", 39143696, Roles.Biologist, Shift.Morning);
    public Account Biologist4 = new Account("Biologist_4", 14343967, Roles.Biologist, Shift.Afternoon);
    public Account Biologist5 = new Account("Biologist_5", 27368967, Roles.Biologist, Shift.Afternoon);
    public Account Biologist6 = new Account("Biologist_6", 42348727, Roles.Biologist, Shift.Afternoon);
    public Account Biologist7 = new Account("Biologist_7", 63348967, Roles.Biologist, Team.Team_A, Shift.Night);
    public Account Biologist8 = new Account("Biologist_8", 41348747, Roles.Biologist, Team.Team_A, Shift.Night);
    public Account Biologist9 = new Account("Biologist_9", 17346967, Roles.Biologist, Team.Team_B, Shift.Night);
    public Account Biologist10 = new Account("Biologist_10", 98348945, Roles.Biologist, Team.Team_B, Shift.Night);


    public Set<Account> accountSet = new HashSet<Account>(){{
        add( Chief_Service );

        add( Doctor );

        add( Receptionist1 );
        add( Receptionist2 );
        add( Receptionist3 );
        add( Receptionist4 );
        add( Receptionist5 );
        add( Receptionist6 );

        add( Biologist1 );
        add( Biologist2 );
        add( Biologist3 );
        add( Biologist4 );
        add( Biologist5 );
        add( Biologist6 );
        add( Biologist7 );
        add( Biologist8 );
        add( Biologist9 );
        add( Biologist10 );
    }};

}