package com.jcomm.sorting;

import com.jcomm.datastructures.CollectionsHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RankSorting {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path = Paths.get(RankSorting.class.getClassLoader()
                .getResource("fixed_length_words").toURI());

        RankSorting rs = new RankSorting();
        rs.keyIndexSorting(Files.lines(path));


    }

    public void rankSorting(Stream<String> lines) {
        var results = lines.map(s -> {
            var tokens = s.split(" ");
            var name = tokens[0];
            var rank = Integer.parseInt(tokens[1]);
            return new Student(name, rank);
        }).collect(Collectors.toList());

        Student[] students = results.toArray(new Student[0]);
        int size = results.size();
        int[] countOfStudentsInSection = new int[6];
        for (int index = 0; index < size; index++) {
            countOfStudentsInSection[students[index].getKey() + 1]++;
        }
        CollectionsHelper.printArray(countOfStudentsInSection);
        for (int i = 1; i < countOfStudentsInSection.length - 1; i++) {
            countOfStudentsInSection[i + 1] += countOfStudentsInSection[i];
        }
        CollectionsHelper.printArray(countOfStudentsInSection);

        Student[] sortedBySection = new Student[size];
        for (int i = 0; i < students.length; i++) {
            sortedBySection[countOfStudentsInSection[students[i].getKey()]++] = students[i];
        }
        CollectionsHelper.printArray(sortedBySection);
        CollectionsHelper.printArray(countOfStudentsInSection);
    }


    public void keyIndexSorting(Stream<String> lines) {
        var licensePlateNumbers = lines.collect(Collectors.toList()).toArray(new String[0]);
        int countOfPlates = licensePlateNumbers.length;
        int alphabetSize = 256;
        String[] aux = new String[countOfPlates];
        for (int letterPosition = 6; letterPosition >= 0; letterPosition--) {
            int[] letterFrequency = new int[alphabetSize + 1];
            for (int i = 0; i < countOfPlates; i++) {
                letterFrequency[licensePlateNumbers[i].charAt(letterPosition) + 1]++;
            }
//            printCurrentStatus(letterFrequency);

            for (int i = 1; i <= alphabetSize; i++) {
                letterFrequency[i] += letterFrequency[i - 1];
            }

            for(int i = 0; i < countOfPlates; i++) {
                aux[letterFrequency[licensePlateNumbers[i].charAt(letterPosition)]++] = licensePlateNumbers[i];
            }

            System.arraycopy(aux, 0, licensePlateNumbers, 0, countOfPlates);

            CollectionsHelper.printArray(licensePlateNumbers);
            CollectionsHelper.printArray(aux);
//            printCurrentStatus(letterFrequency);
        }


    }

    private void printCurrentStatus(int[] letterFrequency) {
        for (int i = 0; i < 256; i++) {
            if (Character.isAlphabetic(i) || Character.isDigit(i)) {
                System.out.print((char) i + "_");
            } else {
                System.out.print(" _");
            }
        }
        System.out.println();
        CollectionsHelper.printArray(letterFrequency);
    }

    private void countFrequency() {

    }

}
