/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve.diagnostics

import com.intellij.openapi.util.Condition
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.Diagnostic
import java.util.ArrayList

public class SimpleDiagnostics(diagnostics: Collection<Diagnostic>) : Diagnostics {
    //copy to prevent external change
    private val diagnostics = ArrayList(diagnostics)

    @Suppress("UNCHECKED_CAST")
    private val elementsCache = DiagnosticsElementsCache(this, Condition.TRUE as Condition<Diagnostic>?)

    override fun all() = diagnostics

    override fun forElement(psiElement: PsiElement) = elementsCache.getDiagnostics(psiElement)

    override fun noSuppression() = this
}
