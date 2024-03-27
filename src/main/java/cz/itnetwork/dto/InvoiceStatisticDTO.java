package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceStatisticDTO {

    private long currentYearSum;

    private long allTimeSum;

    private long invoicesCount;

}
