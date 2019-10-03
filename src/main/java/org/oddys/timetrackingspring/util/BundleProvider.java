package org.oddys.timetrackingspring.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleProvider {
    private final String BASE_NAME = "i18n.messages";
    private final String DEFAULT_LANGUAGE = "en";

    public ResourceBundle getBundle(String language) {
        return ResourceBundle.getBundle(BASE_NAME,
                new Locale(language != null ? language : DEFAULT_LANGUAGE));
    }
}
