/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.object;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

public class ObjectCopyUtil {

    /** logger */
    private static Log logger = LogFactory.getLog(ObjectCopyUtil.class);

    //
    /**
     * Returns a deepCopy of the object, or null if the object cannot
     * be serialized.
     *
     * @param orig original object
     * @return copied object
     */
    public static Object deepCopy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a deepCopy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            logger.error("Object input stream error", e);
        }
        catch(ClassNotFoundException cnfe) {
            logger.error("Object class not found error", cnfe);
        }
        return obj;
    }

}
