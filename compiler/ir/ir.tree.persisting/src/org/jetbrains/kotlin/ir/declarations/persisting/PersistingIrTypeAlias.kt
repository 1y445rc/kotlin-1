/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.persisting

import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.persisting.carriers.Carrier
import org.jetbrains.kotlin.ir.declarations.persisting.carriers.TypeAliasCarrier
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name

class PersistingIrTypeAlias(
    override val startOffset: Int,
    override val endOffset: Int,
    override val symbol: IrTypeAliasSymbol,
    override val name: Name,
    override var visibility: Visibility,
    override val expandedType: IrType,
    override val isActual: Boolean,
    origin: IrDeclarationOrigin
) :
    PersistingIrDeclarationBase<TypeAliasCarrier>,
    IrTypeAlias,
    TypeAliasCarrier {

    init {
        symbol.bind(this)
    }

    override var lastModified: Int = stageController.currentStage
    override var loweredUpTo: Int = stageController.currentStage
    override var values: Array<Carrier>? = null
    override val createdOn: Int = stageController.currentStage

    override var parentField: IrDeclarationParent? = null
    override var originField: IrDeclarationOrigin = origin
    override var removedOn: Int = Int.MAX_VALUE
    override var annotationsField: List<IrConstructorCall> = emptyList()

    @ObsoleteDescriptorBasedAPI
    override val descriptor: TypeAliasDescriptor
        get() = symbol.descriptor

    override var typeParametersField: List<IrTypeParameter> = emptyList()

    override var typeParameters: List<IrTypeParameter>
        get() = getCarrier().typeParametersField
        set(v) {
            if (typeParameters !== v) {
                setCarrier().typeParametersField = v
            }
        }
}
