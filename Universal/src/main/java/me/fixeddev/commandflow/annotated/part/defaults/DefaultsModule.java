package me.fixeddev.commandflow.annotated.part.defaults;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.annotated.annotation.ConsumedArgs;
import me.fixeddev.commandflow.annotated.annotation.Flag;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.annotated.annotation.Text;
import me.fixeddev.commandflow.annotated.part.AbstractModule;
import me.fixeddev.commandflow.annotated.part.Key;
import me.fixeddev.commandflow.annotated.part.defaults.factory.ArgumentStackPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.BooleanPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.ContextFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.DoublePartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.FlagPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.FloatPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.IntegerPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.StringPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.factory.StringTextPartFactory;
import me.fixeddev.commandflow.annotated.part.defaults.modifier.LimitModifier;
import me.fixeddev.commandflow.annotated.part.defaults.modifier.OptionalModifier;
import me.fixeddev.commandflow.part.defaults.BooleanPart;
import me.fixeddev.commandflow.part.defaults.FloatPart;
import me.fixeddev.commandflow.part.defaults.IntegerPart;
import me.fixeddev.commandflow.stack.ArgumentStack;

public class DefaultsModule extends AbstractModule {
    @Override
    public void configure() {
        BooleanPartFactory booleanPartFactory = new BooleanPartFactory();
        bindFactory(boolean.class, booleanPartFactory);
        bindFactory(Boolean.class, booleanPartFactory);

        DoublePartFactory doublePartFactory = new DoublePartFactory();
        bindFactory(double.class, doublePartFactory);
        bindFactory(Double.class, doublePartFactory);

        FloatPartFactory floatPartFactory = new FloatPartFactory();
        bindFactory(Float.class, floatPartFactory);
        bindFactory(float.class, floatPartFactory);

        IntegerPartFactory partFactory = new IntegerPartFactory();
        bindFactory(int.class, partFactory);
        bindFactory(Integer.class, partFactory);

        bindFactory(new Key(String.class, Text.class), new StringTextPartFactory());
        bindFactory(CommandContext.class, new ContextFactory());
        bindFactory(ArgumentStack.class, new ArgumentStackPartFactory());

        FlagPartFactory flagPartFactory = new FlagPartFactory();

        bindFactory(new Key(boolean.class, Flag.class), flagPartFactory);
        bindFactory(new Key(Boolean.class, Flag.class), flagPartFactory);

        bindModifier(ConsumedArgs.class, new LimitModifier());
        bindModifier(OptArg.class, new OptionalModifier());
    }
}
