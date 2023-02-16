package tn.esprit.Entitys;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="Appointment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "reason" )
    String reason;

    @Column(name = "date_sending" )
    Date date_sending;

    @Column(name = "comments" )
    String comments;

    @Column(name = "is_first_visit" )
    boolean is_first_visit ;

    @ManyToOne()
    Account account;

    public Appointment(String reason, Date date_sending, String comments, boolean is_first_visit) {
         this.reason=reason;  this.date_sending=date_sending;  this.comments=comments;  this.is_first_visit=is_first_visit;
    }
}
