import db.DBHandle;
import db.IntField;
import db.StringField;
import db.buffers.LocalBufferFile;

import java.io.File;
import java.io.IOException;

import ghidra.util.exception.*;

import java.text.MessageFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        args = new String[]{"C:\\Users\\reije\\Documents\\Development\\decompilation\\GhidraExporter\\Project1.rep\\idata\\00\\~00000005.db\\db.2.gbf"};
        try {
            if (args.length != 1)
                throw new Exception("Invalid argument list length");
            var dbFile = new File(args[0]);
            if (!dbFile.exists())
                throw new Exception("File does not exist");
            var bf = new LocalBufferFile(dbFile, true);
            var handle = new DBHandle(bf);
            var tables = handle.getTables();
            for (var table : tables) {
                System.out.println(table.getName() + "(records: " + table.getRecordCount() + ")");
                try {
                    var it = table.iterator();
                    while (it.hasNext()) {
                        System.out.println("New record of table " + table);
                        var record = it.next();
                        for (var i = 0; i < 200; i++) {
                            if (record.getFieldValue(i) instanceof StringField)
                                System.out.println(record.getString(i));
                        }
                    }
                }catch(Exception ex){}
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}