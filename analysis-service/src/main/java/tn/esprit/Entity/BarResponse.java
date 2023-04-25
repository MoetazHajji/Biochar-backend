package tn.esprit.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BarResponse {
    private List<String> labels;
    private List<Long> values;
}
