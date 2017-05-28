package com.finances.utils;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class UtilsCSV {
    public Set<String> getAllSymbols(final String fileName) throws FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        CSVReader csvReader = new CSVReader(new FileReader(file));
        Set<String> symbols = new HashSet<>();
        for (String[] cells : csvReader) {
            symbols.add(cells[0]);
        }
        return symbols;
    }
}
