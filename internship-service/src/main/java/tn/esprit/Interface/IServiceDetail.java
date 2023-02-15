package tn.esprit.Interface;

import tn.esprit.Entity.Detail;

import java.util.List;

public interface IServiceDetail {
    Detail addDetail(Detail d);

    Detail updateDetail(Detail d);

    void DeleteDetail(Integer idDetail);

    List<Detail> getAllDetail();

    Detail findDetail(Integer idDetail);
}
