import java.util.List;

public class TM3UItem {

    public int ID = 0;
    public String Group = "";
    public String Name = "";
    public String URL = "";

    private boolean Check(String Str, String Frm) {
        return Str.indexOf(Frm) != -1;
    }

    private void setID(String INF) throws ExceptionInvalidFormat {

        if (!Check(INF, TM3UKeyWords.EXTINF))
            throw new ExceptionInvalidFormat();

        int n1 = INF.indexOf(":");
        if (n1 == -1)
            throw new ExceptionInvalidFormat();

        int n2 = INF.indexOf(",", n1);
        if (n2 == -1)
            throw new ExceptionInvalidFormat();

        String s = INF.substring(n1 + 1, n2);
        ID = Integer.parseInt(s);

    }

    private void setName(String INF) throws ExceptionInvalidFormat {

        if (!Check(INF, TM3UKeyWords.EXTINF))
            throw new ExceptionInvalidFormat();

        int n1 = INF.indexOf(",");
        if (n1 == -1)
            throw new ExceptionInvalidFormat();

        Name = INF.substring(n1 + 1).trim();

    }

    private void setGroup(String GRP) throws ExceptionInvalidFormat {

        if (!Check(GRP, TM3UKeyWords.EXTGRP))
            throw new ExceptionInvalidFormat();

        int n1 = GRP.indexOf(":");
        if (n1 == -1)
            throw new ExceptionInvalidFormat();

        Group = GRP.substring(n1 + 1).trim();

    }

    private void setURL(String LNK) {
        URL = LNK;
    }

    private void fillLine1(List<String> data) {
        data.add(TM3UKeyWords.EXTINF + ":" + Integer.toString(ID) + "," + Name);
    }

    private void fillLine2(List<String> data) {
        data.add(TM3UKeyWords.EXTGRP + ":" + Group);
    }

    private void fillLine3(List<String> data) {
        data.add(URL);
    }

    public void setData(String INF, String GRP, String LNK) throws ExceptionInvalidFormat {
        setID(INF);
        setName(INF);
        setGroup(GRP);
        setURL(LNK);
    }

    public void fillData(List<String> data) {
        fillLine1(data);
        fillLine2(data);
        fillLine3(data);
    }


}
