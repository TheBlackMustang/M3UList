import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TM3UList extends ArrayList<TM3UItem> {

    public void Load(String Filename) throws IOException, ExceptionInvalidFormat {
        List<String> lns = Files.readAllLines(Paths.get(Filename));
        if (!TM3UKeyWords.HEADER.equalsIgnoreCase(lns.get(0)))
            throw new ExceptionInvalidFormat();
        lns.remove(0);
        if ((Math.round(lns.size() / 3) * 3) != lns.size())
            throw new ExceptionInvalidFormat();
        for (int i = 0; i < lns.size(); i = i + 3) {
            TM3UItem item = new TM3UItem();
            item.setData(lns.get(i), lns.get(i + 1), lns.get(i + 2));
            add(item);
        }
    }

    public void Save(String Filename) throws IOException {
        List<String> lns = new ArrayList<>();
        lns.add(TM3UKeyWords.HEADER);
        for (TM3UItem t: this) {
            t.fillData(lns);
        }
        Files.write(Paths.get(Filename), lns);
    }

    public int FindByName(String Name) {
        for (int i = 0; i < this.size(); i++) {
            if (Name.equalsIgnoreCase(this.get(i).Name)) {
                return i;
            }
        }
        return -1;
    }

}
