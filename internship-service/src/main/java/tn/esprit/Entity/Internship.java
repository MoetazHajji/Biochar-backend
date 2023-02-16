package tn.esprit.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Internship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull
    String certificate;
    @Enumerated(EnumType.STRING)
    State state;
    @ManyToOne
    Subject subject;
    @OneToMany(mappedBy = "internship",cascade = CascadeType.PERSIST)
    Set<InternshipRequest> internshipRequests;
    @OneToOne(cascade = CascadeType.PERSIST)
    Detail detail;
    @OneToMany(mappedBy = "internship",cascade = CascadeType.PERSIST)
    Set<FollowUpSheet> followUpSheets;


}
