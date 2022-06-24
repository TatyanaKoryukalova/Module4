package filework.storage;

import filework.entities.Writable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Хранилище файлов для записываемых сущностей (Writable)
 * Позволяет производить операции с файловой системой
 *
 * @param <T> тип записываемых сущностей
 */
public interface Storage<T extends Writable> {
    /**
     * Записывает поля указанного объекта в файл
     *
     * @param item   - указанный объект
     * @param parent - директория назначения, в которой создается файл пользователя
     * @return - полное название созданного файла с расширением
     * @throws IOException - в случае, когда пользователь с такий id уже существует,
     *                     либо если файл не может быть создан
     */
    String writeIntoFile(T item, File parent) throws IOException;

    /**
     * Читает из файла с указанным именем строку с полями объекта
     * и возвращает созданный из нее объект типа T
     *
     * @param parent   - директория, в которой находится указанный файл
     * @param filename - имя файла для чтения
     * @return - объект типа T из указанного файла
     * @throws IOException - в случае, если указанного файла не существует
     */
    T readFromFile(File parent, String filename) throws IOException;

    /**
     * Удаляет указанный файл
     *
     * @param parent   - директория, в которой находится указанный файл
     * @param filename - имя указанного файла
     * @return - true если файл успешно удален
     * @throws FileNotFoundException - в случае, когда указанного файла не существует в директории
     */
    boolean deleteFile(File parent, String filename) throws FileNotFoundException;

    /**
     * Возвращает массив типа String, элементы которого - имена файлов в указанной директории
     *
     * @param parent - указанная директория
     * @return список файлов в виде массива строк
     */
    String[] getListOfFiles(File parent);

    /**
     * Возвращает имя директории для каждой конкретной сущности
     *
     * @return имя директории
     */
    String getSubDir();
}
