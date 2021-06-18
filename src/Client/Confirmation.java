package Client;

public class Confirmation extends NachrichtDummy
{
    private int empfangenhash;

    public Confirmation(String art, String sender, String inhalt, int hashCode)
    {
        super(art, sender, inhalt);
        this.empfangenhash = hashCode;
    }

}
