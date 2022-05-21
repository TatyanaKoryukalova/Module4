package filework;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * Класс для работы с файлами, в которых записаны объекты User
 */
public class UserFileWorker {

    /**
     * Записывает поля указанного пользователя в файл
     *
     * @param user      - указанный пользователь
     * @param directory - директория назначения, в которой создается файл пользователя
     * @return - полное название созданного файла с расширением
     * @throws IOException - в случае, когда пользователь с такий id уже существует,
     *                     либо если файл не может быть создан
     */
    public String writeUserIntoFile(User user, String directory) throws IOException {
        String filename = "id" + user.getId() + ".csv";
        File userFile = new File(directory, filename);
        if (userFile.exists()) {
            throw new FileAlreadyExistsException("User with this id already exist");
        }
        FileWriter writer = new FileWriter(directory + filename);
        writer.write(user.toString());
        writer.close();
        return filename;
    }

    /**
     * Читает из файла с указанным именем строку с полями пользователя
     * и возвращает созданный из нее объект User
     *
     * @param directory - директория, в которой находится указанный файл
     * @param filename  - имя файла для чтения
     * @return - объект User из указанного файла
     * @throws IOException - в случае, если указанного файла не существует
     */
    public User readUserFromFile(String directory, String filename) throws IOException {
        checkExistenceFile(new File(directory, filename));
        BufferedReader reader = new BufferedReader(new FileReader(directory + filename));
        String line = reader.readLine();
        String[] userArray = line.split(",");
        reader.close();
        return new User(userArray);
    }

    /**
     * Удаляет указанный файл пользователя
     *
     * @param directory - директория, в которой находится указанный файл
     * @param filename  - имя указанного файла
     * @return - true если файл успешно удален
     * @throws FileNotFoundException - в случае, когда указанного файла не существует в директории
     */
    public boolean deleteUserFile(String directory, String filename) throws FileNotFoundException {
        File fileForDeleting = new File(directory, filename);
        checkExistenceFile(fileForDeleting);
        fileForDeleting.delete();
        return true;
    }

    /**
     * Возвращает массив типа String, элементы которого - имена файлов в указанной директории
     *
     * @param directory - указанная директория
     * @return список файлов в виде массива строк
     */
    public String[] getListOfFiles(String directory) {
        return new File(directory).list();
    }

    /**
     * Проверяет существование файла
     *
     * @param file - указанный файл
     * @throws FileNotFoundException - в случае, если файла не существует
     */
    public void checkExistenceFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
    }
}