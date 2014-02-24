package com.technophobia.substeps.idea;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;

/**
 * Created by alan on 23/02/14.
 */
public interface SubstepsElementTypes {
    IFileElementType FILE = new IStubFileElementType(SubstepsLanguage.INSTANCE);

}
