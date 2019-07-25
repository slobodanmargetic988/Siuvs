package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.repository.PageRepository;
import org.bitbucket.pbosko.siuvs.valueObject.PageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public Page findOne(PageId pageId) {
        return pageRepository.findOne(pageId.getValue());
    }

    @Override
    public List<Page> getTopLevelItems() {
        return pageRepository.findAllByParentIdNullOrderByOrderAsc();
    }

}
