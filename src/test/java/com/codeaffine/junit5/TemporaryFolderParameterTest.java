package com.codeaffine.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

public class TemporaryFolderParameterTest {

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void useTempFolderParameter(TemporaryFolder tempFolder) throws IOException {
        File file = tempFolder.newFile("foo");

        Assertions.assertTrue(file.exists());
    }

}
