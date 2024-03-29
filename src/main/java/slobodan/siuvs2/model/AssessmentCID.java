package slobodan.siuvs2.model;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

public class AssessmentCID {


    private int id;


    private int client;


    private int page;

    private int consequences;


    private int probability;

    private String textPojavljivanje;

    private String textProstornaDimenzija;

    private String textIntenzitet;

    private String textVreme;

    private String textTok;

    private String textTrajanje;

    private String textRanaNajava;

    private String textPripremljenost;

    private String textUticaj;

    private String textGenerisanjeDrugihOpasnosti;

    private String textReferentniIncidenti;

    private String textInformisanjeJavnosti;

    private String textBuduceInformacije;
    
    
    
    public AssessmentCID kopiraj(Assessment dok){
AssessmentCID newdoc= new AssessmentCID();
newdoc.setClient(dok.getClient().getId());
newdoc.setId(dok.getId());
newdoc.setPage(dok.getPage().getId());
newdoc.copyDataFieldsFromAssessment(dok);
return newdoc;
}

    public void setId(int id) {
        this.id = id;
    }
   

    public int getId() {
        return id;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getConsequences() {
        return consequences;
    }

    public void setConsequences(int consequences) {
        this.consequences = consequences;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

   

    public void copyDataFieldsFromAssessment(Assessment assessment) {
        this.setProbability(assessment.getProbability());
        this.setConsequences(assessment.getConsequences());
        this.setTextPojavljivanje(assessment.getTextPojavljivanje());
        this.setTextProstornaDimenzija(assessment.getTextProstornaDimenzija());
        this.setTextIntenzitet(assessment.getTextIntenzitet());
        this.setTextVreme(assessment.getTextVreme());
        this.setTextTok(assessment.getTextTok());
        this.setTextTrajanje(assessment.getTextTrajanje());
        this.setTextRanaNajava(assessment.getTextRanaNajava());
        this.setTextPripremljenost(assessment.getTextPripremljenost());
        this.setTextUticaj(assessment.getTextUticaj());
        this.setTextGenerisanjeDrugihOpasnosti(assessment.getTextGenerisanjeDrugihOpasnosti());
        this.setTextReferentniIncidenti(assessment.getTextReferentniIncidenti());
        this.setTextInformisanjeJavnosti(assessment.getTextInformisanjeJavnosti());
        this.setTextBuduceInformacije(assessment.getTextBuduceInformacije());

    }

    public String getTextPojavljivanje() {
        return textPojavljivanje;
    }

    public void setTextPojavljivanje(String textPojavljivanje) {
        this.textPojavljivanje = textPojavljivanje;
    }

    public String getTextProstornaDimenzija() {
        return textProstornaDimenzija;
    }

    public void setTextProstornaDimenzija(String textProstornaDimenzija) {
        this.textProstornaDimenzija = textProstornaDimenzija;
    }

    public String getTextIntenzitet() {
        return textIntenzitet;
    }

    public void setTextIntenzitet(String textIntenzitet) {
        this.textIntenzitet = textIntenzitet;
    }

    public String getTextVreme() {
        return textVreme;
    }

    public void setTextVreme(String textVreme) {
        this.textVreme = textVreme;
    }

    public String getTextTok() {
        return textTok;
    }

    public void setTextTok(String textTok) {
        this.textTok = textTok;
    }

    public String getTextTrajanje() {
        return textTrajanje;
    }

    public void setTextTrajanje(String textTrajanje) {
        this.textTrajanje = textTrajanje;
    }

    public String getTextRanaNajava() {
        return textRanaNajava;
    }

    public void setTextRanaNajava(String textRanaNajava) {
        this.textRanaNajava = textRanaNajava;
    }

    public String getTextPripremljenost() {
        return textPripremljenost;
    }

    public void setTextPripremljenost(String textPripremljenost) {
        this.textPripremljenost = textPripremljenost;
    }

    public String getTextUticaj() {
        return textUticaj;
    }

    public void setTextUticaj(String textUticaj) {
        this.textUticaj = textUticaj;
    }

    public String getTextGenerisanjeDrugihOpasnosti() {
        return textGenerisanjeDrugihOpasnosti;
    }

    public void setTextGenerisanjeDrugihOpasnosti(String textGenerisanjeDrugihOpasnosti) {
        this.textGenerisanjeDrugihOpasnosti = textGenerisanjeDrugihOpasnosti;
    }

    public String getTextReferentniIncidenti() {
        return textReferentniIncidenti;
    }

    public void setTextReferentniIncidenti(String textReferentniIncidenti) {
        this.textReferentniIncidenti = textReferentniIncidenti;
    }

    public String getTextInformisanjeJavnosti() {
        return textInformisanjeJavnosti;
    }

    public void setTextInformisanjeJavnosti(String textInformisanjeJavnosti) {
        this.textInformisanjeJavnosti = textInformisanjeJavnosti;
    }

    public String getTextBuduceInformacije() {
        return textBuduceInformacije;
    }

    public void setTextBuduceInformacije(String textBuduceInformacije) {
        this.textBuduceInformacije = textBuduceInformacije;
    }

}
