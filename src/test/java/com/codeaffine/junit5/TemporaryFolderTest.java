package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderTest {

    TemporaryFolder tempFolderField;

    @Test
    public void useTempFolder(TemporaryFolder tempFolderParameter) throws IOException {
        Path file1 = tempFolderField.newFile("foo");
        Path file2 = tempFolderParameter.newFile("foo");

        Assertions.assertNotSame(tempFolderField, tempFolderParameter);
        Assertions.assertTrue(file1.toFile().exists());
        Assertions.assertTrue(file2.toFile().exists());
        Assertions.assertNotEquals(file1, file2);
    }

    @Test
    public void shouldGetValidPath() {
        assertNotNull(tempFolderField.toPath());
    }
}
