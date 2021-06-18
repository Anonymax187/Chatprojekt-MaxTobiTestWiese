package Client;

import java.time.LocalDateTime;
import java.util.Objects;

public class NachrichtDummy
{
    private String art;
    private String sender;
    private String inhalt;
    private LocalDateTime gesendet;
    private int hashCode;

    public NachrichtDummy()
    {

    }

    public NachrichtDummy(String inhalt)
    {
        art = "Nachricht";
        this.inhalt = inhalt;
        takeTime();
        this.hashCode = hashCode();
    }

    public NachrichtDummy(String art, String sender, String inhalt)
    {
        this.art = art;
        this.sender = sender;
        this.inhalt = inhalt;
        takeTime();
        hashCode = hashCode();
    }

    public NachrichtDummy(String inhalt, int hashCode)
    {
        this.inhalt = inhalt;
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return hashCode;
    }

    public String getArt() {
        return art;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public int hashCode()
    {
        int hashWert = 17;
        hashWert = Objects.hash(hashWert, inhalt);
        hashWert = Objects.hash(hashWert, gesendet);
        return hashWert;
    }
    public void takeTime()
    {
        gesendet = LocalDateTime.now();
    }


}
