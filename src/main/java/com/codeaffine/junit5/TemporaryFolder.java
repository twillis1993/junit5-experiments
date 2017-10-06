package com.codeaffine.junit5;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class TemporaryFolder {
    private Path rootFolder;

    public Path newFile(String name) throws IOException {
        return Files.createFile(Paths.get(rootFolder.toString(), name));
    }

    public Path toPath() {
        return rootFolder;
    }

    void prepare() {
        try {
            // TODO why do this?
            rootFolder = Files.createTempFile("junit5-", ".tmp");
            Files.delete(rootFolder);

            Files.createDirectory(rootFolder);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    void cleanUp() {
        try {
            Files.walkFileTree(rootFolder, new DeleteAllVisitor());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static class DeleteAllVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
            Files.delete(file);
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path directory, IOException exception) throws IOException {
            Files.delete(directory);
            return CONTINUE;
        }
    }

}