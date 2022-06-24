package filework.storage;

import filework.entities.Writable;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;


public abstract class FileStorage<T extends Writable> implements Storage<T> {

    @Override
    public String writeIntoFile(T item, File directory) throws IOException {
        String filename = item.getFilename() + ".csv";

        File file = new File(directory, filename);
        if (file.exists()) {
            throw new FileAlreadyExistsException("Object with this id already exist");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(item.toString());
        writer.close();
        return filename;
    }

    @Override
    public T readFromFile(File directory, String filename) throws IOException {
        File file = new File(directory, filename);
        checkExistenceFile(file);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] itemArray = line.split(",");
        reader.close();
        return deserialize(itemArray);
    }

    abstract T deserialize(String[] itemArray);

    @Override
    public boolean deleteFile(File directory, String filename) throws FileNotFoundException {
        File fileForDeleting = new File(directory, filename);
        checkExistenceFile(fileForDeleting);
        fileForDeleting.delete();
        return true;
    }

    @Override
    public String[] getListOfFiles(File directory) {
        return directory.list();
    }

    private void checkExistenceFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
    }
}