package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.valueObject.PageId;

import java.util.List;

public interface PageService {

    Page findOne(PageId pageId);

    List<Page> getTopLevelItems();

}
