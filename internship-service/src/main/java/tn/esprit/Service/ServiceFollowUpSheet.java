package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.FollowUpSheet;
import tn.esprit.Interface.IServiceFollowUpSheet;
import tn.esprit.Repository.RepoFollowUpSheet;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceFollowUpSheet implements IServiceFollowUpSheet {
    RepoFollowUpSheet rfs;
    @Override
    public FollowUpSheet addFollowUpSheet(FollowUpSheet f) {
        return rfs.save(f);
    }

    @Override
    public void DeleteFollowUpSheet(Integer idFollowUpSheet) {
        rfs.deleteById(idFollowUpSheet);

    }
    @Override
    public List<FollowUpSheet> getAllFollowUpSheet() {
        List<FollowUpSheet> FollowUpSheetList = new ArrayList<>();
        rfs.findAll().forEach(FollowUpSheetList::add);
        return FollowUpSheetList;
    }
    @Override
    public FollowUpSheet findFollowUpSheet(Integer idFollowUpSheet) {
        return rfs.findById(idFollowUpSheet).orElse(null);
    }





}
