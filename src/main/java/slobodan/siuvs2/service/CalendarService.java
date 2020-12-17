package slobodan.siuvs2.service;

import java.util.List;
import slobodan.siuvs2.model.Client;

import slobodan.siuvs2.model.Calendar;

import slobodan.siuvs2.valueObject.CalendarId;

public interface CalendarService {

    void save(Calendar calendar);

List<Calendar> findAllBy();

    List<Calendar> findAllByClient(Client client);

    Calendar findById(CalendarId calendarId);
}
