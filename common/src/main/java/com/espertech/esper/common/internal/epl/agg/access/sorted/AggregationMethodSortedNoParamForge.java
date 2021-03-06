/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.common.internal.epl.agg.access.sorted;

import com.espertech.esper.common.client.hook.aggmultifunc.AggregationMultiFunctionAggregationMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpression;
import com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol;
import com.espertech.esper.common.internal.epl.agg.core.AggregationMethodForge;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.*;

public class AggregationMethodSortedNoParamForge implements AggregationMethodForge {

    private final Class underlyingClass;
    private final AggregationMethodSortedEnum aggMethod;
    private final Class resultType;

    public AggregationMethodSortedNoParamForge(Class underlyingClass, AggregationMethodSortedEnum aggMethod, Class resultType) {
        this.underlyingClass = underlyingClass;
        this.aggMethod = aggMethod;
        this.resultType = resultType;
    }

    public CodegenExpression codegenCreateReader(CodegenMethodScope parent, SAIFFInitializeSymbol symbols, CodegenClassScope classScope) {
        CodegenMethod method = parent.makeChild(AggregationMultiFunctionAggregationMethod.class, this.getClass(), classScope);
        method.getBlock()
            .methodReturn(staticMethod(AggregationMethodSortedNoParamFactory.class, "makeSortedAggregationNoParam", enumValue(AggregationMethodSortedEnum.class, aggMethod.name()), constant(underlyingClass)));
        return localMethod(method);
    }

    public Class getResultType() {
        return resultType;
    }
}
