package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import slobodan.siuvs2.model.Calendar;

import slobodan.siuvs2.repository.CalendarRepository;
import slobodan.siuvs2.valueObject.CalendarId;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    
     @Override
    public void save(Calendar calendar){
        calendarRepository.save(calendar);
    }

 @Override
    public List<Calendar> findAllBy(){
    return calendarRepository.findAll();
    }

    @Override
    public  List<Calendar> findAllByClient(Client client){
    return calendarRepository.findAllByClient(client);
    }

    @Override
    public Calendar findById(CalendarId calendarId){
    return calendarRepository.findById(calendarId.getValue());
            
         
    }
        @Override
   public Calendar findFirstByClientAndDokument(Client client, String dokument){
     return calendarRepository.findFirstByClientAndDokument( client, dokument);
    }
}
