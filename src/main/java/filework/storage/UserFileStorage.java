package filework.storage;

import filework.entities.User;

public class UserFileStorage extends FileStorage<User> {
    private static final String SUB_DIR = "User";

    @Override
    User deserialize(String[] valuesArray) {
        return new User(valuesArray[0], valuesArray[1], valuesArray[2],
                Integer.parseInt(valuesArray[3]), Integer.parseInt(valuesArray[4]));
    }

    @Override
    public String getSubDir() {
        return SUB_DIR;
    }
}
