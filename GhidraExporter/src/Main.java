import db.*;
import db.buffers.LocalBufferFile;

import java.io.File;
import java.io.IOException;

import ghidra.util.exception.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            var dir = "C:\\Users\\reije\\Project2.rep";
            var gbfFiles = Files.walk(Paths.get(dir))
                    .filter(file -> file.toString().endsWith(".gbf"))
                    .collect(Collectors.toSet());
            for (var gbfFile : gbfFiles) {
                var dbFile = new File(gbfFile.toString());
                if (!dbFile.exists())
                    throw new Exception("File does not exist");
                var bf = new LocalBufferFile(dbFile, true);
                var handle = new DBHandle(bf);
                var table = handle.getTable("Label History");
                    var it = table.iterator();
                    System.out.println("<functions>");
                    while (it.hasNext()) {
                        var record = it.next();
                        System.out.printf("<function line=\"%s\" name=\"%s\" />%n", Long.toHexString(record.getLongValue(0)), record.getString(2));
                    }
                    System.out.println("</functions>");
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }
}