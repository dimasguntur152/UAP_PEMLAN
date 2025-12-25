package org.example;
import java.io.*;
import java.util.*;

public class ItemRepository
{
    private static final String DATA_FILE = "data/items.txt";
    private static final String HISTORY_FILE = "data/history.txt";

    public static List<Item> getAllRaw()
    {
        List<Item> list = new ArrayList<>();
        try
        {
            File f = new File(DATA_FILE);
            if (!f.exists()) { f.getParentFile().mkdirs(); f.createNewFile(); return list; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
            {
                if (!line.trim().isEmpty()) list.add(Item.fromFile(line));
            }
            br.close();
        } catch (Exception e) {}
        return list;
    }

    public static void saveOrUpdate(Item newItem) throws IOException
    {
        List<Item> all = getAllRaw();
        boolean found = false;
        for (int i = 0; i < all.size(); i++)
        {
            if (all.get(i).getId().equals(newItem.getId()))
            {
                all.set(i, newItem);
                found = true;
                break;
            }
        }
        if (!found) all.add(newItem);
        overwrite(all);
    }

    public static void deleteToHistory(Item item) throws IOException
    {
        List<Item> all = getAllRaw();
        all.removeIf(i -> i.getId().equals(item.getId()));
        overwrite(all);
        FileWriter fw = new FileWriter(HISTORY_FILE, true);
        fw.write(item.toFileString() + "\n");
        fw.close();
    }

    public static void overwrite(List<Item> items) throws IOException
    {
        FileWriter fw = new FileWriter(DATA_FILE);
        for (Item i : items) fw.write(i.toFileString() + "\n");
        fw.close();
    }
}