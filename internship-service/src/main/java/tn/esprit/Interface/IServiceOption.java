package tn.esprit.Interface;

import tn.esprit.Entity.Option;

import java.util.List;

public interface IServiceOption {


    Option addOption(Option o, int questionid);

    void DeleteOption(Integer idOption);

    List<Option> getAllOption();

    Option findOption(Integer idOption);
}
