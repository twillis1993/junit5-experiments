package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderTest {

    TemporaryFolder tempFolderField;

    @Test
    public void useTempFolder(TemporaryFolder tempFolderParameter) throws IOException {
        File file1 = tempFolderField.newFile("foo");
        File file2 = tempFolderParameter.newFile("foo");

        Assertions.assertNotSame(tempFolderField, tempFolderParameter);
        Assertions.assertTrue(file1.exists());
        Assertions.assertTrue(file2.exists());
        Assertions.assertNotEquals(file1, file2);
    }
}
