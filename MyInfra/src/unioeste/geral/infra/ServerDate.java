package unioeste.geral.infra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ServerDate {
    public static DateTimeFormatter formatter() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return form;
    }

}
