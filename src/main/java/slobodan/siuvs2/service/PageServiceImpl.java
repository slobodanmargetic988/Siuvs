package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.repository.PageRepository;
import slobodan.siuvs2.valueObject.PageId;
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
