package com.technophobia.substeps.idea;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by alan on 22/01/14.
 */
public class SubstepsToken extends IElementType {
    public SubstepsToken(@NotNull @NonNls String debugName) {
        super(debugName, SubstepsLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SubstepsToken." + super.toString();
    }
}
