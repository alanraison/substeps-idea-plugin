package com.technophobia.substeps.idea.parsing;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.technophobia.substeps.idea.SubstepsLanguage;

/**
 * Created by alan on 24/02/14.
 */
public interface SubstepsFileElementTypes {
    IFileElementType FILE = new IStubFileElementType(SubstepsLanguage.INSTANCE);
}
