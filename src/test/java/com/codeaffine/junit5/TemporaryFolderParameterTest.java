package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Path;

public class TemporaryFolderParameterTest {

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void useTempFolderParameter(TemporaryFolder tempFolder) throws IOException {
        Path file = tempFolder.newFile("foo");

        Assertions.assertTrue(file.toFile().exists());
    }

}
