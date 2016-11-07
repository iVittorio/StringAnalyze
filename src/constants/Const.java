package constants;

/**
 * Created by i.viktor on 05/11/2016.
 */
public class Const {
    public static final String URL_VALIDATE_REGEX = "\\w+://.*";
    public static final String DELIMITER_REGEX = "[^А-Яа-яa-zёЁA-Z0-9_]+";
    public static final String CYRILLIC_CHAR_REGEX = "[А-Яа-яёЁ0-9_]+";
    public static final String NO_SUCH_FILE_MESSAGE = "ОШИБКА! Файл не найден ";
    public static final String FIND_FOREIGN_LANG_MESSAGE = "ОШИБКА! Текст содержит иннострные слова! ";
    public static final String STATUS_TEMPLATE = "%-15s количество вхождений: %8d%n";
    public static final String RESULT_TEMPLATE = "%-15s встретилось %8d раз%n";
}
