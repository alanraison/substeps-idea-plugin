package com.technophobia.substeps.idea;

import com.intellij.lang.Language;

/**
 * Created by alan on 18/01/14.
 */
public class SubstepsLanguage extends Language {
    public static SubstepsLanguage INSTANCE = new SubstepsLanguage();
    private SubstepsLanguage() {
        super("Substeps");
    }
}
