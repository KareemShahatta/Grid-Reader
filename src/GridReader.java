import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class GridReader
{
    private char[][] grid;
    private String filename;
    File file;

    public GridReader(String filename) throws FileNotFoundException
    {
        this.filename = filename;
        file = new File(filename);
        if(file.exists())
        {
            initializeGrid(file);
            populateGrid(file);
        }
    }
    public String getFileName()
    {
        return filename;
    }



    public char[][] getCopy()
    {
        if(file.exists())
        {
            char[][] copyGrid = new char[grid.length][];
            for(int r = 0 ; r < grid.length; r++)
            {
                copyGrid[r] = new char[grid[r].length];

                for(int c = 0 ; c < grid[r].length ; c++)
                {
                    copyGrid[r][c] = grid[r][c];
                }
            }
            return copyGrid;
        }
        else
        {
            return null;
        }
    }

    private void populateGrid(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);

        for(int r = 0 ; r < grid.length ; r++)
        {
            char[] chars = scanner.nextLine().toCharArray();

            for(int c = 0 ; c < grid[r].length ; c++)
            {
                grid[r][c] = chars[c];
            }
        }

        scanner.close();
    }

    private void initializeGrid(File file) throws FileNotFoundException
    {
        initializeGridRows(file);
        initializeGridColumns(file);


    }
    private void initializeGridRows(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        int rows = 0;

        do
        {
            rows++;
            scanner.nextLine();
        }
        while(scanner.hasNextLine());

        grid = new char[rows][];

        scanner.close();
    }
    private void initializeGridColumns(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);

        for(int i = 0 ; i < grid.length ; i ++)
        {
            grid[i] = new char[scanner.nextLine().length()];
        }

        scanner.close();
    }

    @Override
    public java.lang.String toString()
    {
        if(file.exists())
        {
            StringBuffer buffer = new StringBuffer();

            for(int r = 0 ; r < grid.length ; r++)
            {
                for(int c = 0 ; c < grid[r].length ; c++)
                {
                    buffer.append(grid[r][c]);
                }

                buffer.append(System.lineSeparator());
            }

            return buffer.toString();
        }
        else
        {
            return null;
        }
    }

    public void extraCredit()
    {
        System.out.println("EXTRA CREDIT");
        System.out.println(toString());
        startGame();
        System.out.println(toString());
    }

    private void startGame()
    {
        Random random = new Random();

        for(int r = 0 ; r < grid.length; r++)
        {
            for(int c = 0 ; c < grid[r].length ; c++)
            {
                if(random.nextBoolean())
                {
                    grid[r][c] = 'O';
                }
                else
                {
                    grid[r][c] = 'X';
                }

                if(isGameFinished())
                {
                    return;
                }
            }
        }

        startGame();
    }
    private boolean isGameFinished()
    {
        if((grid[0][0] == grid[0][1]) && (grid[0][1] == grid[0][2])) //Horizontal Top
        {
            return true;
        }
        else if((grid[1][0] == grid[1][1]) && (grid[1][1] == grid[1][2]))  //Horizontal Center
        {
            return true;
        }
        else if((grid[2][0] == grid[2][1]) && (grid[2][1] == grid[2][2]))  //Horizontal Bottom
        {
            return true;
        }
        else if((grid[0][0] == grid[1][0]) && (grid[1][0] == grid[2][0])) //Vertical Left
        {
            return true;
        }
        else if((grid[0][1] == grid[1][1]) && (grid[1][1] == grid[2][1]))  //Vertical Center
        {
            return true;
        }
        else if((grid[0][2] == grid[1][2]) && (grid[1][2] == grid[2][2]))  //Vertical Right
        {
            return true;
        }
        else if((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]))  //Diagonal #1
        {
            return true;
        }
        else if((grid[0][2] == grid[1][1]) && (grid[1][1] == grid[2][0]))  //Diagonal #1
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}