/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.model.PodRezultat;
import org.springframework.stereotype.Service;

@Service
public class PodRezultatFactoryImpl implements PodRezultatFactory {

    @Override
    public PodRezultat empty(Rezultat rezultat) {
        PodRezultat podRezultat = new PodRezultat();
        podRezultat.setRezultat(rezultat);
        podRezultat.setAktivnostiText("Нова активност");
        podRezultat.setBudzetJls(0);
        podRezultat.setBudzetNeobezbedjeno(0);
        podRezultat.setBudzetOstalo(0);
        podRezultat.setIndikatoriText("/");
        podRezultat.setOdgovornaInstitucijaText("/");
        podRezultat.setPartnerInstitucijaText("/");
        podRezultat.setRedosled(0);
        podRezultat.setPeriodKompletiran(0);
        podRezultat.setKratkoObrazlozenje("/");
        podRezultat.setPeriodText("/");
        return podRezultat;
    }

}
