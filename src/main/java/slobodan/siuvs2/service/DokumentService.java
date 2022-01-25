package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import java.util.List;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.PhotoId;

public interface DokumentService {

    void save(Dokument dokument);
    void save(Client client,InternationalAgreements ia,String title, String filename);
    void save(Client client,PublicPolicyDocuments ppd,String title, String filename);
    Dokument findById(Integer dokumentId);
    Dokument findByIa(InternationalAgreements ia);
    Dokument findByPpd(PublicPolicyDocuments ppd);
    List<Dokument> findAllByClientId(Client client);
    void delete(Client client, int docId);
    String findFileNameById(DokumentID DokumentID);
      List<Dokument> findAll();
}
