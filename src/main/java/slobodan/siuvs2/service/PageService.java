package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.valueObject.PageId;

import java.util.List;

public interface PageService {

    Page findOne(PageId pageId);

    List<Page> getTopLevelItems();

}
