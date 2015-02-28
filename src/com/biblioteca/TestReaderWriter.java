package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 27/02/15.
 */
public class TestReaderWriter implements InputOutput {

    private String output;
    private String input;
    @Override
    public String readValue() throws IOException {
        return this.input;
    }

    @Override
    public void writeValue(String string) {
        this.output = string;
    }

    public String consoleOutput() {
        return this.output;
    }

    public String consoleInput(String input) {
        this.input=input;
        return input;
    }
}
