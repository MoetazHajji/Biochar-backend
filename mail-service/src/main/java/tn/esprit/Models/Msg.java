package tn.esprit.Models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Msg {
    Long id;
    String subject;
    //@Email
    //@NotNull
    String email;

    //@NotNull
    //@Min(10)
    String body;

    List<BodyContent> bodyContents = new ArrayList<BodyContent>();

    Date  sentDate;

    Date  receivedDate;
    List<Attachment>  attachements = new ArrayList<Attachment>();

    boolean html;

     public Msg(String subject, String email, String body) {
        this.subject = subject;
        this.email = email;
        this.body = body;
    }


    public Msg(String subject, String email, String body, List<Attachment> attachements) {
        this.subject = subject;
        this.email = email;
        this.body = body;
        this.attachements = attachements;
    }

}
/*
* up to JDK7:
@AllArgsConstructor(onConstructor=@__({@AnnotationsGoHere}))
from JDK8:
@AllArgsConstructor(onConstructor_={@AnnotationsGohere}) // note the underscore after onConstructor.*/