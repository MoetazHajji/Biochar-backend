package tn.esprit.Models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment {
     String id;
     String fileName;
     String fileType;

     long fileSize;
     byte[] data ;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public Attachment(String fileName) {
        this.fileName = fileName;
    }



    public Attachment(String fileName, String fileType, long fileSize, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.data = data;
    }

    public String _getNameFile_ ( ){
        if (fileName == null) {return null;}
        String[] words = fileName.split("/"); // split the string into words using the space character as a delimiter
        return ( words.length == 0 ? null : words[words.length-1]) ;
    }
}
