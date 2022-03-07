package slobodan.siuvs2.model;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

public class DokumentCID {


    private int id;

    private Integer client;


    private InternationalAgreements ia;
    

    private PublicPolicyDocuments ppd;


    private String title;

    private String filename;


    
public DokumentCID kopiraj(Dokument dok){
DokumentCID newdoc= new DokumentCID();
newdoc.setClient(dok.getClient().getId());
newdoc.setId(dok.getId());
newdoc.setIa(dok.getIa());
newdoc.setPpd(dok.getPpd());
newdoc.setFilename(dok.getFilename());
newdoc.setTitle(dok.getTitle());

return newdoc;
}
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    

    public InternationalAgreements getIa() {
        return ia;
    }

    public void setIa(InternationalAgreements ia) {
        this.ia = ia;
    }

    public PublicPolicyDocuments getPpd() {
        return ppd;
    }

    public void setPpd(PublicPolicyDocuments ppd) {
        this.ppd = ppd;
    }
    
    
}
