import java.io.FileNotFoundException;

class GameFileTester
{
    public static void main(String argsp[]) throws FileNotFoundException
    {
        String filename = "GameFile.txt";
        GridReader reader = new GridReader(filename);
        System.out.println(reader);
    }
}