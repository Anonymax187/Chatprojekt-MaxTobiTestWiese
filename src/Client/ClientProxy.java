package Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientProxy implements Runnable
{
    PrintWriter writer;
    BufferedReader reader;
    DataOutputStream writeint;
    DataInputStream readerint;
    Socket client;
    ClientController c;
    ArrayList<String> nachrichtEmpfangen = new ArrayList<String>();

    public ClientProxy(Socket client, ClientController c)
    {
        this.c = c;
        this.client = client;

        try
        {
            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
            writeint = new DataOutputStream(out);
            readerint = new DataInputStream(in);
            writer = new PrintWriter(out);
            reader = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            System.out.println("Fehler in ClientProxy Constructor");
        }
    }

    public void run()
    {
        try
        {
            String s = null;

            NachrichtDummy dummy;


            while ((s = reader.readLine()) != null)
            {
                dummy = new NachrichtDummy(s);
                String[] parts = s.split("#");
                nachrichtEmpfangen.add(parts[1]);
                System.out.println(parts[1]);
                c.textWindow.appendText(parts[0]);
                gelesen(parts[1]);
            }
            /*
            while ((b = readerint.readInt()) != 0)
            {
                c.textWindow.appendText(b +"\n");
            }*/


            //writer.close();
            //reader.close();
        } catch (Exception e) {
            System.out.println("Fehler in ClientProxy Constructor");
        }
    }

    public  void schreiben(String s)
    {
        writer.write(s + "\n");
        writer.flush();
    }
    public void gelesen(String vergleichCode)
    {
        for(String hashCode : nachrichtEmpfangen)
        {
            if(vergleichCode.equals(hashCode))
            {
                c.textWindow.appendText("       gelesen" + hashCode + "\n");
            }
        }
    }

}
