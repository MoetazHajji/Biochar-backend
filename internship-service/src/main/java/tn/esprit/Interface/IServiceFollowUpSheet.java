package tn.esprit.Interface;

import tn.esprit.Entity.FollowUpSheet;

import java.util.List;

public interface IServiceFollowUpSheet {
    FollowUpSheet addFollowUpSheet(FollowUpSheet f);



    void DeleteFollowUpSheet(Integer idFollowUpSheet);

    List<FollowUpSheet> getAllFollowUpSheet();

    FollowUpSheet findFollowUpSheet(Integer idFollowUpSheet);
}
