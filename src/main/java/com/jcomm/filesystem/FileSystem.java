package com.jcomm.filesystem;

public class FileSystem {

    //each file can only be 8 characters
    private final int MAX_FILE_SIZE;
    private final int MAX_CHARS_SIZE = 8;

    private int maxCharsLeft = 0;

    private int remainingFiles = 0;
    private FileNode head;

    private FileSystem(int numOfFiles) {
        this.MAX_FILE_SIZE = numOfFiles;
        maxCharsLeft = MAX_CHARS_SIZE * MAX_CHARS_SIZE;
        remainingFiles = MAX_FILE_SIZE;
    }


    public void write(String data) throws Exception {

        if (data.length() > maxCharsLeft) {
            throw new Exception("data size exceeds the number of file nodes available");
        }

        if (data.length() % 8 != 0) {
            throw new Exception("Data must be a multiple of " + MAX_CHARS_SIZE);
        }


        if (data.length() / 8 > remainingFiles) {
            throw new Exception("data size exceeds file availability");
        }

        int count = data.length() / 8;
        int ptr = 0;

        FileNode node = head;
        FileNode prev = head;
        while (count > 0) {
            node = new FileNode();
            node.setNext(prev);
            node.write(data.substring(ptr, ptr + MAX_CHARS_SIZE));
            ptr += MAX_CHARS_SIZE;
            prev = node;
            count--;
            remainingFiles--;
        }
        head = node;
    }


    public void print() {
        var current = new FileNode();
        current = head;
        int count = MAX_FILE_SIZE;
        while (current != null) {
            System.out.print(current);
            if (current.getNext() != null) {
                System.out.print("->");
            }
            current = current.getNext();
            count--;
        }

        while (count > 0) {
            if(count == MAX_FILE_SIZE){
                System.out.print("[]");
            } else {
                System.out.print("->[]");
            }
            count--;
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        FileSystem fs = new FileSystem(5);
        fs.print();
        fs.write("12345678abcdefghjovaughn1ovaughn123aughn");
        fs.print();

//        fs.write("abcdefgh");
//        fs.print();
//
//        fs.write("jovaughn");
//        fs.print();
//
//        fs.write("1ovaughn");
//        fs.print();
//
//        fs.write("123aughn");
//        fs.print();
//
//        fs.write("123aughn");
//        fs.print();
    }

}
