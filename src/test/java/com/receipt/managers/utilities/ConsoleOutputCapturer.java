package com.receipt.managers.utilities;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ConsoleOutputCapturer {
    private ByteArrayOutputStream baos;
    private PrintStream previous;
    private boolean capturing;

    public void start() {
        if (capturing) {
            return;
        }

        capturing = true;
        previous = System.out;      
        baos = new ByteArrayOutputStream();

        OutputStream outputStreamCombiner = 
                new OutputStreamCombiner(Arrays.asList(previous, baos)); 
        PrintStream custom = new PrintStream(outputStreamCombiner);

        System.setOut(custom);
    }

    public String stop() {
        if (!capturing) {
            return "";
        }

        System.setOut(previous);

        String capturedValue = baos.toString();             

        baos = null;
        previous = null;
        capturing = false;

        return capturedValue;
    }
}