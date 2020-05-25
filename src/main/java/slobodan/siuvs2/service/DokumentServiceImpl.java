package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import slobodan.siuvs2.repository.PhotoRepository;
import slobodan.siuvs2.valueObject.PhotoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.repository.DokumentRepository;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DokumentID;

@Service
public class DokumentServiceImpl implements DokumentService {

    @Autowired
    private DokumentRepository DokumentRepository;

    @Override
    public void save(Dokument dokument) {
    DokumentRepository.save(dokument);
    }
    
    @Override
    public void save(Client client,InternationalAgreements ia,String title, String filename) {
        Dokument dokument=new Dokument();
        dokument.setClient(client);
        dokument.setIa(ia);
        dokument.setTitle(title);
        dokument.setFilename(filename);
    DokumentRepository.save(dokument);
    }
       @Override
    public void save(Client client,PublicPolicyDocuments ppd,String title, String filename) {
        Dokument dokument=new Dokument();
        dokument.setClient(client);
        dokument.setPpd(ppd);
        dokument.setTitle(title);
        dokument.setFilename(filename);
    DokumentRepository.save(dokument);
    }
    @Override
    public Dokument findById(Integer dokumentId){
    return DokumentRepository.findById(dokumentId);
    }
    
       @Override
    public Dokument findByIa(InternationalAgreements ia){
    return DokumentRepository.findByIa(ia);
    }
        @Override
    public Dokument findByPpd(PublicPolicyDocuments ppd){
    return DokumentRepository.findByPpd(ppd);
    }
    @Override
    @Transactional
    public void delete(Client client, int docId) {
        Dokument dokument = DokumentRepository.findOne(docId);
        if (dokument.getClient().getId() == client.getId()) {
            DokumentRepository.deleteById(docId);
        }
    }
    @Override
    public String findFileNameById(DokumentID DokumentID) {
        Dokument dokument = DokumentRepository.findOne(DokumentID.getValue());
        return dokument.getFilename();
    }
    @Override
    public List<Dokument> findAllByClientId(Client client){
        return DokumentRepository.findByClientOrderByIdAsc(client);
    }
}