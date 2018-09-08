package pl.mateusz.NBPrateSecurity.models.forms;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeRateNBP {

    private String numberTableNBP;
    private String dateOfListing;
    private String datePublication;
    private String currencyName;
    private int converter;
    private String currencyCode;
    private Double avarageRate;
    private Double buyRate;
    private Double sellRate;
}
