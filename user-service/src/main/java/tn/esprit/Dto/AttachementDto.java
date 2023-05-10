package tn.esprit.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class AttachementDto {
    private String id;
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
