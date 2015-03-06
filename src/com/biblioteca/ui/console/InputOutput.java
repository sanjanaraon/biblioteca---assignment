package com.biblioteca.ui.console;

import java.io.IOException;

/**
 * Created by sanjanar on 27/02/15.
 */
public interface InputOutput {
    public String readValue() throws IOException;
    public void writeValue(String string) throws IOException;


}
