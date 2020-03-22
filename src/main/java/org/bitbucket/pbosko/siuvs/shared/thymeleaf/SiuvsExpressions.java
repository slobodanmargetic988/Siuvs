package org.bitbucket.pbosko.siuvs.shared.thymeleaf;

import org.bitbucket.pbosko.siuvs.shared.NumberHelper;

public class SiuvsExpressions {

    public String formatNumber(String text) {
        
        return NumberHelper.getDisplayNumber(text);
    }

}