package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Cookie;
import tn.esprit.Entity.Subject;
import tn.esprit.Entity.Training;
import tn.esprit.External.Profile;
import tn.esprit.Interface.ISubjectService;
import tn.esprit.Repository.CookiesRepository;
import tn.esprit.Repository.SubjectRepository;
import tn.esprit.Repository.TrainingRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class SubjectService implements ISubjectService {
    final SubjectRepository subjectRepository;

    final CookiesRepository cookiesRepository;


    final TrainingRepository trainingRepository;

    final Connections connections;

    // @Scheduled(cron = "* * 8 * * *")

    public List<Subject> add_Data(MultipartFile file) {
        // Map<String, List<String>> data = new HashMap<>();
        List<Subject> subjects = new ArrayList<>();
        Map<String, Integer> indexes = new HashMap<>();
        Boolean[] check_headers = {false, false, false, false};
        Boolean header = true;

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (header) {
                    for (Cell cell : row) {
                        switch (cell.toString().toLowerCase().trim()) {
                            case "title":
                                indexes.put("title", cell.getColumnIndex());
                                check_headers[0] = true;
                                break;
                            case "description":
                                indexes.put("description", cell.getColumnIndex());
                                check_headers[1] = true;
                                break;
                            case "complexity":
                                indexes.put("complexity", cell.getColumnIndex());
                                check_headers[2] = true;
                                break;
                        /*    case "priority":
                                indexes.put("priority", cell.getColumnIndex());
                                check_headers[3] = true;
                                break;*/
                            default:
                                break;
                        }
                    }
                    header = false;
                } else if (check_headers[0]) {
                    Subject subject = row_handling(row, indexes, check_headers);
                    if (subject != null)
                        subjects.add(subject);
                } else
                    throw new Exception("Missing Title");
            }

            // log.info("subjects : " + subjects.toString());
            Delete_all();
            return subjectRepository.saveAll(subjects);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    private int String_to_Int(String value) {
        try {
            int number_value = Integer.parseInt(value.substring(0, value.indexOf('.')));
            if (number_value / 10 == 0 && number_value >= 0)
                return number_value;
            throw new Exception("bad number");
        } catch (Exception e) {
            return 0;
        }
    }

    private Subject row_handling(Row row, Map<String, Integer> indexes, Boolean[] check_headers) {
        Subject subject = new Subject();
        subject.setTitle(row.getCell(indexes.get("title")).toString());

        if (check_headers[1]) {
            try {

                subject.setDescription(row.getCell(indexes.get("description")).toString());
            } catch (Exception e) {
            }
        }
        if (check_headers[2]) {
            try {
                subject.setComplexity(String_to_Int(row.getCell(indexes.get("complexity")).toString().trim()));
            } catch (Exception e) {
            }

        }
     /*   if (check_headers[3]) {
            try {
                subject.setPriority(String_to_Int(row.getCell(indexes.get("priority")).toString().trim()));
            } catch (Exception e) {
            }
        }*/
        // log.info("valid");
        return subject;

    }

    @Override
    public void Delete_all() {
        subjectRepository.deleteAll();
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void add_Cookies(javax.servlet.http.Cookie[] cookies,int id_user) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Cookie> cookieList = new ArrayList<>();


        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> {

                Cookie cookie1 = new Cookie();
                cookie1.setName(cookie.getName());
                cookie1.setValue(cookie.getValue());
                cookie1.setPath(cookie.getPath());
                cookie1.setDomain(cookie.getDomain());
                cookie1.setUser(id_user);
                cookie1.setDate(date);
                if(cookie_validation(cookie1,id_user))
                {
                    cookieList.add(cookie1);
                    log.info("valid");
                }

            });
            if(cookieList.size() > 0)
            {
                log.info("added");
                cookiesRepository.saveAll(cookieList);
            }


        }
    }

    @Override
    @Transactional
    public void add_Cookies_File(MultipartFile multipartFile,int id_user) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            List<Cookie> cookies = new ArrayList<>();
            // ClassLoader classLoader = getClass().getClassLoader();
            File file = File.createTempFile("temp", null);
            multipartFile.transferTo(file);
            String[] data = FileUtils.readFileToString(file, "UTF-8").split("\n");

            for (int i = 4;i<data.length;i++) {
                String[] values = data[i].split("\\s+");

                if (values[0] != "") {
                    Cookie cookie = new Cookie();
                    cookie.setDomain(domain_cleanup(values[0].trim()));
                    cookie.setPath(values[2].trim());
                    cookie.setName(values[4].trim());
                    cookie.setValue(values[5].trim());
                    cookie.setDate(date);
                    cookie.setUser(id_user);
                    if(cookie_validation(cookie,id_user))
                        cookies.add(cookie);
                }
                //Arrays.stream(values).forEach(v -> log.info("--> " + v));
            }
            // log.info(cookies.toString());
            if(cookies.size() > 0)
                cookiesRepository.saveAll(cookies);
        } catch (IOException ioe) {
        }

    }

    @Override
    public Iterable<Cookie> getAllCookies() {
        return cookiesRepository.findAll();
    }

    @Override
    public void clearCookies() {
        cookiesRepository.deleteAll();
    }


    // @Scheduled(fixedRate = 100000)
    private String getUrls(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String content = restTemplate.getForObject(url, String.class);
            return content;
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("url is not valid!");
            return "";
        } catch (Exception e) {
            log.error("Error!");
            return "";
        }
    }

    // @Scheduled(fixedDelay = 100000)
    public List<String> cookies_domains(List<Cookie> cookies) {

        List<String> urls_pages = new ArrayList<>();
        for(Cookie cookie: cookies) {
            urls_pages.add(sites_cleanup(getUrls("https://"+cookie.getDomain()+cookie.getPath())));
        }
        return urls_pages;


    }

    private String domain_cleanup(String domain)
    {
        try {
            domain = domain.substring(1);
            domain = domain.replace("HttpOnly_.", "");
            return domain;
        }
        catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException)
        {
            log.error(" empty : " + domain);
            return "";
        }

    }

    private String sites_cleanup(String site)
    {
        site = site.replaceAll("<.*?>", "");
        String cleaned_text = "";
        String[] lines = site.split("\n");
        for (String line : lines)
        {
            if(line.trim() !="" && !line.contains(";") && !line.contains("{") && !line.contains("}") && !line.contains("<") && !line.contains(">") && !line.contains(":"))
                cleaned_text += "\n" + line.trim();
        }


        return cleaned_text;
    }




    private void create_result_text(Map<String,Integer> map) throws IOException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
        File file = new File("src\\main\\resources\\Results\\"+date.format(formatters)+".txt");

        String content = "";// "=== " +date + " ===\n";
        FileWriter writer = new FileWriter(file);

        for (Map.Entry values : map.entrySet())
        {
            content +=values.getKey() + " : " + values.getValue().toString() + "\n";
        }

        writer.write(content);

        writer.close();
    }


    private List<String> most_suggested(Map<String,Integer> map)
    {
        int count = subjectRepository.getNumber();
        count /=4;

        //List<Integer> max = getMax(map,count);
        return getMax(map,count);

        //  return max.stream().map(value -> getKeyByValue(map,value)).collect(Collectors.toList());
    }

    private List<String> getMax(Map<String,Integer> map,int count)
    {
        log.info(map.toString());
        return map.entrySet()
                .stream()
                //  .map(entry -> entry.getValue())
                .sorted(Comparator.comparingInt(value -> -value.getValue()))
                .limit(count)
                .map(value -> value.getKey())
                .collect(Collectors.toList());
    }
    private  <K, V> K getKeyByValue(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }




    private List<String> get_Knowledges(String subject_title)
    {
        List<Profile> profiles = connections.getProfiles();
        List<String> selected = new ArrayList<>();
        profiles.forEach(profile -> {
            String infos = profile.getKnowledge() + profile.getSkills() ;
            if(infos.toLowerCase().contains(subject_title.toLowerCase()))
                selected.add(connections.getAccountEmailById(profile.getAccount_id()));
        });
        return selected;
    }

    private Map<String,List<String>> get_acquainted(List<String> subjects_title,Map<String,List<String>> suited)
    {
        subjects_title.forEach(subject_title ->
                {
                    suited.put(subject_title,get_Knowledges(subject_title));
                }
        );
        return  suited;
    }

    private Map<String,List<String>> get_recommended(List<String> subjects_title)
    {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Map<String,List<String>> listed =new HashMap<>();
        Map<String,List<String>> suited = new HashMap<>();
        suited = get_acquainted(subjects_title, suited);
        for (Map.Entry<String,List<String>> profiles : suited.entrySet())
        {
            List<String> profiles_email= profiles.getValue();
            Map<String,Double> group_byratings = new HashMap<>();
            for(String email : profiles_email)
            {
                List<Training> trainings = trainingRepository.getTrainingsByEmailAndDate(date,email);

                double x = 0;
                if(trainings.size() > 0)
                {
                    group_byratings.put(email,trainings.stream().map(training -> get_profile_reviews(training)).reduce((double) 0, (a, b) -> a + b));
                }
                else
                    group_byratings.put(email, (double) -1);
            }
            List<String> ordered =  group_byratings.entrySet().stream().sorted(Comparator.comparingDouble(rating -> rating.getValue())).map(rating -> {
                if(rating.getValue() == -1)
                    return rating.getKey() + "(new)";
                else
                    return rating.getKey();
            }).collect(Collectors.toList());
            Collections.reverse(ordered);
            listed.put(profiles.getKey(),ordered);

        }

        return listed;
    }

    private double get_profile_reviews(Training training)
    {

        int ratings = training.getReviews().stream().map(review -> review.getRating()).reduce(0, (a, b) -> a + b);
        return ratings / training.getReviews().size();

    }


    public Map<String,List<String>> Predictions()  {
        List<Subject> subjects = subjectRepository.findAll();
        List<Cookie> cookies = cookiesRepository.findAll();
        List<String> results = cookies_domains(cookies);
        Map<String,Integer> recourences = new HashMap<>();
        try {
            for(Subject subject: subjects)
            {
                int x =0;

                for(Cookie cookie : cookies)
                {
                    if(cookie.getPath().toLowerCase().contains(subject.getTitle().toLowerCase()))
                        x+=2;
                }

                for(String result :results)
                {
                    if(result.toLowerCase().contains(subject.getTitle().toLowerCase()))
                        x++;
                }
                recourences.put(subject.getTitle(),x);
            }
            try {
                create_result_text(recourences);
                List<String> most_reaserched = most_suggested(recourences);
                return get_recommended(most_reaserched);


            }
            catch (IOException ioException)
            {
                log.error("IO erreur!");

            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException)
        {
            log.error("cookies are empty!");

        }
        return null;
    }


    @Scheduled(cron = "0 0 0 */7 * *")
    public void clear_old_cookies()
    {
        LocalDate localDate = LocalDate.now();
        localDate.minusDays(7);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        cookiesRepository.deleteByDateBefore(date);
    }


    private Boolean cookie_validation(Cookie cookie,int user)
    {
        if(cookiesRepository.findFirstByUserAndDomainAndPath(user, cookie.getDomain() ,cookie.getPath()).orElse(null) == null)
            return true;
        return false;
    }

}