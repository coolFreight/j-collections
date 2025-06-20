package com.jcomm.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JFileReader {

    private final List<String> lines;
    private int position;

    private final String fileName;


    /**
     * Assumes the file is in the resources location of the project
     *
     * @param fileName
     */
    public JFileReader(String fileName) {

        this.fileName = fileName;
        URL fileLocation = getClass().getClassLoader().getResource(fileName);
        if(fileLocation == null) {
            throw new RuntimeException("Could not locate file: "+fileName+" in resources directory");
        }

        try {
            lines = Files.readAllLines(Path.of(fileLocation.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNextLine() {
        if(position >= lines.size()) {
            throw new RuntimeException("end of file");
        }
        System.out.print(" "+fileName+" line "+(position+1)+" ");
        return lines.get(position++);
    }

    public boolean hasMoreLines() {
        return position < lines.size();
    }


    public static void main(String[] args) throws FileNotFoundException {
        JFileReader fr = new JFileReader("fixed_length_words");


        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());
        System.out.println(fr.getNextLine());

    }




}
