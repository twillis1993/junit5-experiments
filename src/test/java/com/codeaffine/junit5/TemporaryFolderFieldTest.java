package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Path;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderFieldTest {

    TemporaryFolder tempFolderField;

    @RepeatedTest(10)
    public void useTempFolderField() throws IOException {
        Path file1 = tempFolderField.newFile("foo");
        Path file2 = tempFolderField.newFile("bar");

        Assertions.assertTrue(file1.toFile().exists());
        Assertions.assertTrue(file2.toFile().exists());
        Assertions.assertEquals(tempFolderField.toPath().toFile().listFiles().length, 2);
    }

    @RepeatedTest(10)
    public void useTempFolderFieldAgain() throws IOException {
        Path file1 = tempFolderField.newFile("foo");
        Path file2 = tempFolderField.newFile("bar");

        Assertions.assertTrue(file1.toFile().exists());
        Assertions.assertTrue(file2.toFile().exists());
        Assertions.assertEquals(tempFolderField.toPath().toFile().listFiles().length, 2);
    }

}
