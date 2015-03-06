package com.biblioteca.ui.console;

import java.io.*;

/**
 * Created by sanjanar on 27/02/15.
 */
public class ConsoleReaderWriter implements InputOutput {
    @Override
    public String readValue() throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        String read=bufferedReader.readLine();
    return read;
    }

    @Override
    public void writeValue(String string) throws IOException {
        System.out.println(string);
    }


}
