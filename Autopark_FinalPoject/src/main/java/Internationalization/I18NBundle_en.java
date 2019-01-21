package Internationalization;

import java.util.ListResourceBundle;

public class I18NBundle_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"index.page.login", "Login"},
            {"index.page.password", "Password"},
            {"index.page.submit", "Submit"},
            {"index.page.lang.en", "English"},
            {"index.page.lang.uk", "Ukrainian"}
    };
}
