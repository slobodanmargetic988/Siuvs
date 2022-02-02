package slobodan.siuvs2.model;

import java.util.List;
import slobodan.siuvs2.controller.SearchController;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public class SearchResults {

    private String navigation;
    private String upit;
    private List<DynamicData> dynd;
    private List<DynamicData> dyndshort;
    private List<DynamicData> dataseach;
    private List<String> links;
    private List< List<SearchController.HeaderRow>> tableheaders;

    private List<List<DynamicData>> datas;
    private int numberofPages;

    public SearchResults() {
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getUpit() {
        return upit;
    }

    public void setUpit(String upit) {
        this.upit = upit;
    }

    public List<DynamicData> getDynd() {
        return dynd;
    }

    public void setDynd(List<DynamicData> dynd) {
        this.dynd = dynd;
    }

    public List<DynamicData> getDyndshort() {
        return dyndshort;
    }

    public void setDyndshort(List<DynamicData> dyndshort) {
        this.dyndshort = dyndshort;
    }

    public List<DynamicData> getDataseach() {
        return dataseach;
    }

    public void setDataseach(List<DynamicData> dataseach) {
        this.dataseach = dataseach;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<List<SearchController.HeaderRow>> getTableheaders() {
        return tableheaders;
    }

    public void setTableheaders(List<List<SearchController.HeaderRow>> tableheaders) {
        this.tableheaders = tableheaders;
    }

    public List<List<DynamicData>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<DynamicData>> datas) {
        this.datas = datas;
    }

    public int getNumberofPages() {
        return numberofPages;
    }

    public void setNumberofPages(int numberofPages) {
        this.numberofPages = numberofPages;
    }

}
