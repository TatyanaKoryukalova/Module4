package filework.entities;

/**
 * Сущности, поддерживающие запись в файл формата .csv
 * В методе toString должны быть перечислены все поля через запятую
 */
public interface Writable {
    /**
     * Возвращает имя файла, который формируется при записи сущности
     * @return имя файла
     */
    String getFilename();
}
