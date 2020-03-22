/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.model.PodRezultat;
import org.springframework.stereotype.Service;

@Service
public class PodRezultatFactoryImpl implements PodRezultatFactory{
    
      @Override
    public PodRezultat empty(Rezultat rezultat) {
        PodRezultat podRezultat = new PodRezultat();
        podRezultat.setRezultat(rezultat);
        podRezultat.setAktivnostiText("Нова активност");
        podRezultat.setBudzetJlsText("/");
        podRezultat.setBudzetNeobezbedjenoText("/");
        podRezultat.setBudzetOstaloText("/");
        podRezultat.setIndikatoriText("/");
        podRezultat.setOdgovornaInstitucijaText("/");
        podRezultat.setPartnerInstitucijaText("/");
        podRezultat.setRedosled(0);
        podRezultat.setPeriodKompletiran(0);
        podRezultat.setPeriodText("/");
        return podRezultat;
    }
    
}
