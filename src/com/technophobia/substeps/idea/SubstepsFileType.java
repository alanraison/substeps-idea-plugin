package com.technophobia.substeps.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by alan on 18/01/14.
 */
public class SubstepsFileType extends LanguageFileType {
    public static final SubstepsFileType INSTANCE = new SubstepsFileType();

    private SubstepsFileType() {
        super(SubstepsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Substeps";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Substeps definition file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "substeps";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }
}
