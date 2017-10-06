package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisabledWhenIntegrationTest {

    class MemberClassCondition implements DisabledWhenCondition {
        @Override
        public boolean isSatisfied() {
            return true;
        }
    }

    @Test
    @DisabledWhen(AlwaysDisabled.class)
    public void shouldNeverBeExecuted() {
        Assertions.fail("Should not be executed");
    }

    @Test
    @DisabledWhen(MemberClassCondition.class)
    public void shouldAlsoNeverBeExecuted() {
        Assertions.fail("Should not be executed");
    }
}
