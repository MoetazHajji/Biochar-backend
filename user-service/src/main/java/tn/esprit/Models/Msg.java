package tn.esprit.Models;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Msg {
    @NotNull
    String subject;

    //@Email
    @NotNull
    String email;

    //@NotNull
    //@Min(10)
    String body;

    String attachement;

    boolean isHtml;

     public Msg(String subject, String email, String body) {
        this.subject = subject;
        this.email = email;
        this.body = body;
    }

    public Msg(String subject, String email, String body, String attachement) {
        this.subject = subject;
        this.email = email;
        this.body = body;
        this.attachement = attachement;
    }
}
/*
* up to JDK7:
@AllArgsConstructor(onConstructor=@__({@AnnotationsGoHere}))
from JDK8:
@AllArgsConstructor(onConstructor_={@AnnotationsGohere}) // note the underscore after onConstructor.*/