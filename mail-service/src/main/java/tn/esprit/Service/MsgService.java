package tn.esprit.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.Models.Msg;

import java.util.ArrayList;
import java.util.List;

@Service( "Msg")
public class MsgService implements IMsgService {
    public static List<Msg> RecieveMsgs = new ArrayList<Msg>();
    public static List<Msg> SendMsgs = new ArrayList<Msg>();

    @Override
    public List<Msg> SelectAll() {
        // return ListAcoount.stream().map(account -> AccountMapper.mapToDto(account)) .collect(Collectors.toList());
        return null;
    }

    @Override
    public Msg SelectBy(long id) {
        return null;
    }

    @Override
    public Msg Insert(Msg object) {
        Long id;
       // if ( MsgService.RecieveMsgs == null ) {   id = 1L; }
         if ( MsgService.RecieveMsgs.size() != 0 ) {
            id = MsgService.RecieveMsgs.get(MsgService.RecieveMsgs.size() - 1).getId();
            id += 1; }
        else {id = 1L;}
        object.setId(id);
        MsgService.RecieveMsgs.add(object);
        return  MsgService.RecieveMsgs.get(MsgService.RecieveMsgs.size() - 1)   ;
    }

    @Override
    public Msg update(Msg object) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }
}
