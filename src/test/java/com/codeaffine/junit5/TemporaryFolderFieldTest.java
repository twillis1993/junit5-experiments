package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Path;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderFieldTest {

    TemporaryFolder tempFolderField;

    @Test
    public void useTempFolderField() throws IOException {
        Path file = tempFolderField.newFile("foo");

        Assertions.assertTrue(file.toFile().exists());
    }
}
