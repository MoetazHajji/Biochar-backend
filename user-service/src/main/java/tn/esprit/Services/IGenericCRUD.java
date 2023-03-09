package tn.esprit.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.Entitys.User;

import java.util.List;

public interface IGenericCRUD<Object> {
    List<Object> SelectAll ();
    Object  SelectBy (long id);
    Object Insert( Object object);
    Object update( Object object);
    boolean delete(  long id );
    ResponseEntity<HttpStatus> deleteAll( );
}
