package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import tn.esprit.External.Account;
import tn.esprit.External.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class Connections {
    private final WebClient webClient;

    public List<Profile> getProfiles()
    {
       return Arrays.stream(webClient.get()
                .uri("http://localhost:9060/biochar/Profile/getAllProfiles")
                .retrieve()
                .bodyToMono(Profile[].class)
                .block())
               .collect(Collectors.toList());
    }

    public String getAccountEmailById(long id) {
        return webClient.get()
                .uri("http://localhost:58147/biochar/user-service/Account/" + id)
                .retrieve()
                .bodyToMono(Account.class)
                .map(Account::getEmail) // add this map() method to extract the email attribute
                .block();
    }
    public Profile getAccountProfileById(long id) {
        return webClient.get()
                .uri("http://localhost:9060/biochar/Profile/getProfileById/" + id)
                .retrieve()
                .bodyToMono(Profile.class)// add this map() method to extract the email attribute
                .block();
    }

/*@Scheduled(fixedDelay = 100000)
    public void test()
    {

       System.out.println(getAccountEmailById(getProfiles().get(0).getAccount_id()));

    }*/

}
