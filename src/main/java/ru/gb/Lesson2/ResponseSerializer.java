package ru.gb.Lesson2;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public interface ResponseSerializer {

    default Reader answerRequest(Path paths) throws IOException {

        return Files.newBufferedReader(paths);

    }

    default Reader dontAnswer(Path path1) throws IOException{
        return new StringReader("<h1>Error!!!</h1>");
    }
}
