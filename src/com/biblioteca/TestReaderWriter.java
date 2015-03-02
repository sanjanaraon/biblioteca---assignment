package com.biblioteca;

import java.io.*;

/**
 * Created by sanjanar on 27/02/15.
 */
public class TestReaderWriter implements InputOutput {

    InputStream in;
    OutputStream out;
    BufferedReader br;

    private String output="";
    private String input;
    @Override
    public String readValue() throws IOException {
        return br.readLine();
    }

    @Override
    public void writeValue(String string) {

        this.output += string;

    }

    public String consoleOutput() {
        return this.output;
    }

    public void consoleInput(String input) {
        in=new ByteArrayInputStream(input.getBytes());
        br=new BufferedReader(new InputStreamReader(in));
    }
}
