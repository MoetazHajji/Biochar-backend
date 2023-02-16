package tn.esprit.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.Entitys.User;

import java.util.List;

public interface IGenericCRUD<Object> {

    List<Object> SelectAll ();
    ResponseEntity<Object>  SelectBy (long id);
    Object Insert( Object object);
    ResponseEntity<Object> update( Object object);
    ResponseEntity<HttpStatus> delete(  long id );
}
