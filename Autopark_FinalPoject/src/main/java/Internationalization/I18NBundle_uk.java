package Internationalization;

import java.util.ListResourceBundle;

public class I18NBundle_uk extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"index.page.login", "Логін"},
            {"index.page.password", "Пароль"},
            {"index.page.submit", "Увійти"},
            {"index.page.lang.en", "Англійська"},
            {"index.page.lang.uk", "Українська"}
    };
}
