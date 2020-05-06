package slobodan.siuvs2.shared.thymeleaf;

import slobodan.siuvs2.shared.NumberHelper;

public class SiuvsExpressions {

    public String formatNumber(String text) {
        
        return NumberHelper.getDisplayNumber(text);
    }

}