package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderFieldTest {

    TemporaryFolder tempFolderField;

    @Test
    public void useTempFolderField() throws IOException {
        File file = tempFolderField.newFile("foo");

        Assertions.assertTrue(file.exists());
    }
}
