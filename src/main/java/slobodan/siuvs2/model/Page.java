package slobodan.siuvs2.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "page_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PageType type;

    @Column(name = "risk_type")
    private int riskType;

    @Column(name = "attachable_photos")
    private boolean attachablePhotos;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Page parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("order ASC")
    private List<Page> children;

    private static Map<Integer,List<Page>> childrenCache = new HashMap<>();

    @Column(name = "order")
    private int order;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PageType getType() {
        return type;
    }

    public int getRiskType() {
        return riskType;
    }

    public void setRiskType(int riskType) {
        this.riskType = riskType;
    }

    public void setType(PageType type) {
        this.type = type;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public List<Page> getChildren() {
        return this.children;
    }

    public void setChildren(List<Page> children) {
        this.children = children;
    }

    public List<Page> getChildrenCached() {
        if (!childrenCache.containsKey(this.getId())) {
            childrenCache.put(this.getId(), this.getChildren());
        }
        return childrenCache.get(this.getId());
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Boolean hasChildren() {
        return this.getChildrenCached().size() > 0;
    }

    public String getRiskTypeIconRelativePath() {
        String result;
        switch (getRiskType()) {
            default: result = "default.png"; break;
            case 1: result = "zemljotresi.png"; break;
            case 2: result = "odroni_klizista_erozije.png"; break;
            case 3: result = "poplave.png"; break;
            case 4: result = "grad.png"; break;
            case 5: result = "olujni_vetar.png"; break;
            case 6: result = "topli_talas.png"; break;
            case 7: result = "susa.png"; break;
            case 8: result = "hladni_talas.png"; break;
            case 9: result = "nedostatak_vode.png"; break;
            case 10: result = "epidemije_pandemije.png"; break;
            case 11: result = "bolesti_zivotinja.png"; break;
            case 12: result = "pozari.png"; break;
            case 13: result = "tehnoloske_nesrece.png"; break;
            case 14: result = "bolesti_biljaka.png"; break;
        }
        return result;
    }

    public boolean isAttachablePhotos() {
        return attachablePhotos;
    }

    public void setAttachablePhotos(boolean attachablePhotos) {
        this.attachablePhotos = attachablePhotos;
    }
}
