package com.jcomm.filesystem;

public class FileNode {

    private String data;
    private FileNode next;

    public void write(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public FileNode getNext() {
        return next;
    }

    public void setNext(FileNode next) {
        this.next = next;
    }

    public boolean isFull() {
        return data != null;
    }

    public String toString() {
        if (data == null) {
            return "[]";
        } else {
            return "[" + data + "]";
        }
    }
}
