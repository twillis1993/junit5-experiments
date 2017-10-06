package com.codeaffine.junit5;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;


public class DisabledWhenExtension implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        ConditionEvaluationResult result = ConditionEvaluationResult.enabled("");
        DisabledWhenCondition disabledCondition = getDisabledWhenCondition(context.getElement(), context.getTestInstance());
        if (disabledCondition.isSatisfied()) {
            result = ConditionEvaluationResult.disabled("Disabled by @DisabledWhen");
        }
        return result;
    }

    private DisabledWhenCondition getDisabledWhenCondition(Optional<AnnotatedElement> element, Object target) {
        Optional<DisabledWhen> annotation = findAnnotation(element, DisabledWhen.class);
        if (annotation.isPresent()) {
            try {
                Class<? extends DisabledWhenCondition> conditionType = annotation.get().value();
                if (isConditionTypeStandalone(conditionType)) {
                    return conditionType.newInstance();
                } else {
                    return conditionType.getDeclaredConstructor(target.getClass()).newInstance(target);
                }
            } catch (ReflectiveOperationException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
        return () -> false;
    }

    private boolean isConditionTypeStandalone(Class<?> conditionType) {
        return !conditionType.isMemberClass() || Modifier.isStatic(conditionType.getModifiers());
    }
}
