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

    void cleanUpAfterEach() {
        try {
            Files.walkFileTree(rootFolder, new DeleteAllButRootVisitor(rootFolder));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    void cleanUpAfterAll() {
        try {
            Files.walkFileTree(rootFolder, new DeleteAllVisitor());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static class DeleteAllButRootVisitor extends SimpleFileVisitor<Path> {

        Path rootFolder;

        public DeleteAllButRootVisitor(Path rootFolder) {
           this.rootFolder = rootFolder;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
            Files.delete(file);
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path directory, IOException exception) throws IOException {
            if(directory != rootFolder)
                Files.delete(directory);
            return CONTINUE;
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