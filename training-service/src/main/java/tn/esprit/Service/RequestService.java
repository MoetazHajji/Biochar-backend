package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Request;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.IRequestService;
import tn.esprit.Repository.RequestRepository;
import tn.esprit.Repository.TrainingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestService implements IRequestService {
    final RequestRepository requestRepository;

    final TrainingRepository trainingRepository;

    @Override
    public Request add_request(Long training_id,Request r) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        r.setDate_r(date);

        Training t = trainingRepository.findById(training_id).orElse(null);
        r.setTraining(t);

        return requestRepository.save(r);
    }

    @Override
    public void delete_request(Long id) {
       requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getAll_request() {

        return requestRepository.getAllRequests();
    }
}
