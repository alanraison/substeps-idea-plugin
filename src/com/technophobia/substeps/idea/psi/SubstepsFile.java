package com.technophobia.substeps.idea.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.technophobia.substeps.idea.SubstepsFileType;
import com.technophobia.substeps.idea.SubstepsLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * Created by alan on 24/02/14.
 */
public class SubstepsFile extends PsiFileBase {
    public SubstepsFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SubstepsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SubstepsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Substeps File";
    }
}
