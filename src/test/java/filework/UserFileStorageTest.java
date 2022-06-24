package filework;

import filework.entities.User;
import filework.storage.Storage;
import filework.storage.UserFileStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;


class UserFileStorageTest {
    private User user1 = new User("Anna", "PrettyGirl", "user", 25, 1);
    private User user2 = new User("Ivan", "PrettyBoy", "user", 19, 2);
    private Storage<User> fileStorage = new UserFileStorage();
    private static final File directory = new File("filesUserTest");

    @BeforeAll
     static void initDir(){
        directory.mkdirs();
    }

    @AfterEach
    void init(){
        clearDirectory();
    }

    @Test
    void writingUserIntoFileReturnsFilename() throws IOException {
        assertThat(fileStorage.writeIntoFile(user1, directory)).isEqualTo("userId1.csv");
    }

    @Test
    void writingUserIntoFileThrowsException() throws IOException {
        fileStorage.writeIntoFile(user1, directory);
        assertThatIOException().isThrownBy(() -> fileStorage.writeIntoFile(user1, directory))
                .withMessage("Object with this id already exist");
    }

    @Test
    void readingUserFromFileReturnsUser() throws IOException {
        String filename = fileStorage.writeIntoFile(user1, directory);
        assertThat(fileStorage.readFromFile(directory, filename)).isEqualTo(user1);
    }

    @Test
    void readingUserFromFileThrowsException() {
        assertThatIOException().isThrownBy(() -> fileStorage.readFromFile(directory, "id1.csv"))
                .withMessage("File not found");
    }

    @Test
    void deleteUserFile() throws IOException {
        String filename = fileStorage.writeIntoFile(user1, directory);
        assertThat(fileStorage.deleteFile(directory, filename)).isTrue();
    }

    @Test
    void deleteUserFileThrowsException() {
        assertThatIOException().isThrownBy(() -> fileStorage.deleteFile(directory, "id1.csv"))
                .withMessage("File not found");
    }

    @Test
    void addingFilesChangesLengthListOfFiles() throws IOException {
        fileStorage.writeIntoFile(user1, directory);
        String[] listOfFiles = fileStorage.getListOfFiles(directory);
        assertThat(listOfFiles.length).isEqualTo(1);

        fileStorage.writeIntoFile(user2, directory);
        listOfFiles = fileStorage.getListOfFiles(directory);
        assertThat(listOfFiles.length).isEqualTo(2);
    }

    @Test
    void listOfFilesContainsAllOfRecordedFiles() throws IOException {
        String filename1 = fileStorage.writeIntoFile(user1, directory);
        String filename2 = fileStorage.writeIntoFile(user2, directory);
        String[] listOfFiles = fileStorage.getListOfFiles(directory);

        assertThat(listOfFiles[0]).isEqualTo(filename1);
        assertThat(listOfFiles[1]).isEqualTo(filename2);
    }

    private void clearDirectory() {
        String[] listOfFiles = directory.list();
        for (String filename : listOfFiles) {
            new File(directory, filename).delete();
        }
    }
}