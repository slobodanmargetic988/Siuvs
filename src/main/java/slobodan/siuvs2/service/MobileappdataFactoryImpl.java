package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Mobileappdata;

@Service
public class MobileappdataFactoryImpl implements MobileappdataFactory {

    @Override
    public  Mobileappdata empty(String opstina,String opasnost) {
        Mobileappdata mobileappdata = new Mobileappdata();
       mobileappdata.setOpstina(opstina);
       mobileappdata.setOpasnost(opasnost);
       mobileappdata.setKlasifikacija("Nije još popunjeno");
       mobileappdata.setTekst("Nije još popunjeno");
       mobileappdata.setLink("");
        return mobileappdata;
    }
}
