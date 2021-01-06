import java.io.*;

public class IO
{
    public static void main(String argv[]) throws IOException
    { 
        String buffer = null;

        if (argv.length != 1) {
            System.out.println("You must supply a file name!");
            System.exit(0);
        }

        FileReader fr     = new FileReader(argv[0]);
        BufferedReader br = new BufferedReader(fr);

        while ((buffer = br.readLine()) != null) {
            System.out.println("[" + buffer + "]");
        } 
    }
}

