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
    ArrayList<Confirmation> nachrichtEmpfangen = new ArrayList<Confirmation>();
    ArrayList<Confirmation> nachrichtGesendet = new ArrayList<Confirmation>();

    public ClientProxy(Socket client, ClientController c)
    {
        this.c = c;
        this.client = client;

        try
        {
            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
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

            Confirmation conf;

            while ((s = reader.readLine()) != null)
            {
                String[] parts = s.split("#");
                conf = new Confirmation(parts[3],parts[2],parts[0],Integer.parseInt(parts[1]));
                nachrichtEmpfangen.add(conf);
                System.out.println(parts[1]);
                c.textWindow.appendText(parts[0]);
                gelesen(Integer.parseInt(parts[1]));
            }


            //writer.close();
            //reader.close();
        } catch (Exception e) {
            System.out.println("Fehler in ClientProxy Run");
        }
    }

    public  void schreiben(String s)
    {
        writer.write(s + "\n");
        writer.flush();
    }
    public void gelesen(int vergleichCode)
    {
        for(Confirmation hashCodegelesen : nachrichtGesendet)
        {
            if(vergleichCode == hashCodegelesen.getHashCode())
            {
                System.out.println("Eigene Nachricht");
            }
            else
            {
                for(Confirmation hashCode : nachrichtEmpfangen)
                {
                    if(vergleichCode == hashCode.getHashCode())
                    {
                        c.textWindow.appendText("       gelesen" + hashCode + "\n");
                    }
                }
            }
        }

    }

}
