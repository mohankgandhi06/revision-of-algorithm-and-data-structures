package com.revision.ctci.gobjectorienteddesign;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KFileSystem {
    public static void main(String[] args) {
        FileSystem ntfs = new FileSystem();
    }
}

/* META variable is used for optimization of search of file/folder
 * - for finding the folder/file we first check if the meta is containing the file/folder.
 *   If present then the path is taken and then we can then navigate in the bTree
 * - If the space is a concern then we can directly search in a binary tree for finding
 *   updating and other processes as needed
 * Other Features like Create, Read, Update, Delete, Rename of File and Create, Delete and Rename of Folder */
class FileSystem {
    FSTree bTree;
    HashMap<String, FileSystemContainer> meta;

    public void createFile() {

    }

    public void createFolder() {

    }

    public void deleteFile() {

    }

    public void deleteFolder() {

    }

    public void moveFile() {

    }

    public void moveFolder() {

    }

    public void findFile() {

    }
}

/* It is an n-ary tree structure and when ever a new file/folder is created the tree will be
 * updated along with the meta under the root directory
 * Also when deleting, renaming, moving we can do the function in unit time */
class FSTree {

}

class FileSystemContainer {
    private long id;
    private String name;
    private Path path;
    private Permission permission;

    FileSystemContainer(long id, String name, Path path, Permission permission) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Path getPath() {
        return path;
    }

    public Permission getPermission() {
        return permission;
    }
}

class Folder extends FileSystemContainer {
    private Icon icon;
    private HashMap<String, File> files;
    private HashMap<String, Folder> folders;

    public Folder(long id, String name, Path path, Permission permission) {
        super(id, name, path, permission);
    }

    public void rename() {

    }

    public void updatePermission() {

    }

    /* Getters */
    public Icon getIcon() {
        return icon;
    }

    public HashMap<String, File> getFiles() {
        return files;
    }

    public HashMap<String, Folder> getFolders() {
        return folders;
    }
}

class File extends FileSystemContainer {
    private Content content;
    private Format format;

    public File(long id, String name, Path path, Permission permission) {
        super(id, name, path, permission);
    }

    public void read() {

    }

    public void update() {

    }

    public void rename() {

    }

    public void updatePermission() {

    }

    /* Getters */
    public Content getContent() {
        return content;
    }

    public Format getFormat() {
        return format;
    }
}

class Content {
    private List<MemoryBar> address;
    private String data;

    Content(List<MemoryBar> address, String data) {
        this.address = address;
        this.data = data;
    }

    public List<MemoryBar> getAddress() {
        return address;
    }

    public String getData() {
        return data;
    }
}

/* .txt .doc .exe .pdf .gif .png etc., */
class Format {
    private String type;

    Format(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Path {
    private LinkedList<Folder> path;

    public Path(LinkedList<Folder> path) {
        this.path = path;
    }

    public LinkedList<Folder> getPath() {
        return path;
    }
}

/* directory, read, write etc., */
class Permission {
    private String type;

    public Permission(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

/* Memory Address are allocated in form of locations and typically it will be like a bar/rectangular stretch of spaces */
class MemoryBar {
    private long from;
    private long to;

    MemoryBar(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }
}