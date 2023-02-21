package tn.esprit.Interface;

import tn.esprit.Entity.Request;

import java.util.List;

public interface IRequestService {
    Request add_request(Long training_id,Request r);
    void delete_request(Long id);
    List<Request> getAll_request();
}
