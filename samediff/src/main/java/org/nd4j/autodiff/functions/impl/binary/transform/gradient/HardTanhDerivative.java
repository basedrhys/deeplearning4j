package org.nd4j.autodiff.functions.impl.binary.transform.gradient;

import org.nd4j.autodiff.ArrayField;
import org.nd4j.autodiff.functions.AbstractBinaryFunction;
import org.nd4j.autodiff.functions.AbstractUnaryFunction;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.opstate.OpState;
import org.nd4j.autodiff.samediff.SameDiff;

public class HardTanhDerivative extends AbstractBinaryFunction<ArrayField> {
    public HardTanhDerivative() {
    }

    public HardTanhDerivative(SameDiff sameDiff, DifferentialFunction<ArrayField> i_v1, DifferentialFunction<ArrayField> i_v2) {
        super(sameDiff, i_v1, i_v2, OpState.OpType.GRADIENT);
    }

    public HardTanhDerivative(SameDiff sameDiff) {
        super(sameDiff);
    }
    @Override
    public ArrayField doGetValue() {
        return sameDiff.getArrayFactory().hardTanhDerivative(larg().getValue(true),rarg().getValue(true));
    }

    @Override
    public double getReal() {
        return Math.floor(arg().getReal());
    }

    @Override
    public DifferentialFunction<ArrayField> diff(DifferentialFunction<ArrayField> i_v) {
        return sameDiff.getFunctionFactory().one(getResultShape()).mul(arg().diff(i_v));
    }


    @Override
    public String functionName() {
        return new org.nd4j.linalg.api.ops.impl.transforms.HardTanhDerivative().name();
    }


}
