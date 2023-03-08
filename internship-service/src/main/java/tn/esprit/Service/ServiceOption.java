package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Option;
import tn.esprit.Entity.Question;
import tn.esprit.Interface.IServiceOption;
import tn.esprit.Repository.RepoOption;
import tn.esprit.Repository.RepoQuestion;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceOption implements IServiceOption {
    RepoOption ro;
    RepoQuestion rq;
    @Override
    public Option addOption(Option o, int questionid) {
        Question q=rq.findById(questionid).orElse(null);
        o.setQuestion(q);
        return ro.save(o);
    }


    @Override
    public void DeleteOption(Integer idOption) {
        ro.deleteById(idOption);

    }
    @Override
    public List<Option> getAllOption() {
        List<Option> OptionList = new ArrayList<>();
        ro.findAll().forEach(OptionList::add);
        return OptionList;
    }

    @Override
    public Option findOption(Integer idOption) {
        return ro.findById(idOption).orElse(null);
    }
}
