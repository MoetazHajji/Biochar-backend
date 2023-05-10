package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.TestResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface TestResultRepository extends JpaRepository<TestResult,Integer> {
    List<TestResult> getTestResultByTest_Sample_Account_Id(Long id);




    @Query("Select count(tr) from TestResult tr where" +
            " YEAR(tr.test.sample.account.dateofbirdh) < 1951 and tr.teest LIKE 'M%' ")
     long countTestResultByDate();

    @Query("Select count(tr) from TestResult tr where " +
            "YEAR(tr.test.sample.account.dateofbirdh) BETWEEN 1951 and 1996 and tr.teest LIKE 'M%'")
    long countTestResultByDat();

    @Query("Select count(tr) from TestResult tr where " +
            " YEAR(tr.test.sample.account.dateofbirdh) > 1996 and tr.teest LIKE 'M%' ")
    long countTestResultByDa();

    List<TestResult> getTestResultsByDate(Date date);

    @Query("Select h from TestResult h where YEAR(h.test.sample.account.dateofbirdh) > 1996 and h.teest LIKE '%mcv%' ")
    TestResult countTestResul();



}



