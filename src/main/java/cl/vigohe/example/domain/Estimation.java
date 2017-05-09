package cl.vigohe.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by vigohe on 07-05-17.
 */

@Data
@AllArgsConstructor
public class Estimation {
    private String company;
    private Integer price;
}
