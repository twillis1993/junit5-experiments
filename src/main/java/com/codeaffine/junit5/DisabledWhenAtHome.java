package com.codeaffine.junit5;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Disables annotated tests when run on my home machine.
 */

public class DisabledWhenAtHome implements DisabledWhenCondition {

    @Override
    public boolean isSatisfied() {
        if(Files.exists(Paths.get("/home/tom/misc"))) {
            return true;
        } else return false;
    }
}
