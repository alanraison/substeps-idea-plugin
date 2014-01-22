package com.technophobia.substeps.idea;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by alan on 22/01/14.
 */
public class SubstepsElement extends IElementType {
    public SubstepsElement(@NotNull @NonNls String debugName) {
        super(debugName, SubstepsLanguage.INSTANCE);
    }
}
