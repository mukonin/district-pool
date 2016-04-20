package util;

import java.io.IOException;

/**
 * @ author Mukonin Oleksandr
 */
public class PersonIOException extends IOException{

    public PersonIOException(String msg) {
        super(msg);
    }

    public PersonIOException(String[] val) throws PersonIOException {

        StringBuilder builder = new StringBuilder();

        builder.append("Invalid input format for String: ");
        builder.append(val[0]);
        builder.append("\n");
        builder.append("For values: ");
        for (int it = 1; it < 4; it++)
        {
            if ( ! (val[it] == null)) {
                builder.append("\n");
                builder.append(val[it]);
            }
        }
        throw new PersonIOException(builder.toString());
    }
}
