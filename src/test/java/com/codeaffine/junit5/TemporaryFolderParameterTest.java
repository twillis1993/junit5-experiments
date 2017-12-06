package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Path;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderParameterTest {

    @Test
    void useTempFolderParameter(TemporaryFolder tempFolder) throws IOException {
        Path file = tempFolder.newFile("foo");

        Assertions.assertTrue(file.toFile().exists());
    }

}
