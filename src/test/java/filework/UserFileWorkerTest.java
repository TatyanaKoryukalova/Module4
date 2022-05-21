package filework;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;


class UserFileWorkerTest {
    private User user1 = new User("Anna", "PrettyGirl", "user", 25, 1);
    private User user2 = new User("Ivan", "PrettyBoy", "user", 19, 2);
    private UserFileWorker fileWorker = new UserFileWorker();
    private static final String DIRECTORY = "C:\\filesusertest\\";

    @Test
    void writingUserIntoFileReturnsFilename() throws IOException {
        clearDirectory();
        assertThat(fileWorker.writeUserIntoFile(user1, DIRECTORY)).isEqualTo("id1.csv");
    }

    @Test
    void writingUserIntoFileThrowsException() throws IOException {
        clearDirectory();
        fileWorker.writeUserIntoFile(user1, DIRECTORY);
        assertThatIOException().isThrownBy(() -> fileWorker.writeUserIntoFile(user1, DIRECTORY))
                .withMessage("User with this id already exist");
    }

    @Test
    void readingUserFromFileReturnsUser() throws IOException {
        clearDirectory();
        String filename = fileWorker.writeUserIntoFile(user1, DIRECTORY);
        assertThat(fileWorker.readUserFromFile(DIRECTORY, filename)).isEqualTo(user1);
    }

    @Test
    void readingUserFromFileThrowsException() {
        clearDirectory();
        assertThatIOException().isThrownBy(() -> fileWorker.readUserFromFile(DIRECTORY, "id1.csv"))
                .withMessage("File not found");
    }

    @Test
    void deleteUserFileSuccessful() throws IOException {
        clearDirectory();
        String filename = fileWorker.writeUserIntoFile(user1, DIRECTORY);
        assertThat(fileWorker.deleteUserFile(DIRECTORY, filename)).isTrue();
    }

    @Test
    void deleteUserFileThrowsException() throws IOException {
        clearDirectory();
        assertThatIOException().isThrownBy(() -> fileWorker.deleteUserFile(DIRECTORY, "id1.csv"))
                .withMessage("File not found");
    }

    @Test
    void addingFilesChangesLengthListOfFiles() throws IOException {
        clearDirectory();
        fileWorker.writeUserIntoFile(user1, DIRECTORY);
        String[] listOfFiles = fileWorker.getListOfFiles(DIRECTORY);
        assertThat(listOfFiles.length).isEqualTo(1);

        fileWorker.writeUserIntoFile(user2, DIRECTORY);
        listOfFiles = fileWorker.getListOfFiles(DIRECTORY);
        assertThat(listOfFiles.length).isEqualTo(2);
    }

    @Test
    void listOfFilesContainsAllOfRecordedFiles() throws IOException {
        clearDirectory();
        String filename1 = fileWorker.writeUserIntoFile(user1, DIRECTORY);
        String filename2 = fileWorker.writeUserIntoFile(user2, DIRECTORY);
        String[] listOfFiles = fileWorker.getListOfFiles(DIRECTORY);

        assertThat(listOfFiles[0]).isEqualTo(filename1);
        assertThat(listOfFiles[1]).isEqualTo(filename2);
    }

    private void clearDirectory() {
        String[] listOfFiles = new File(DIRECTORY).list();
        for (String filename : listOfFiles) {
            new File(DIRECTORY + filename).delete();
        }
    }
}