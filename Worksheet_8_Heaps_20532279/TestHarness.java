
/*****************************************************
 * Author  : Vishmi Kalansooriya
 * Date    : 03/10/2021
 * Purpose : To test the methods implemented in the DSAHeap class.
 */
import java.util.*;
import java.io.*;

public class TestHarness {
  public static void main(String args[]) {
    readLargeDataFile();
    // testOne();
  }

  public static void testOne() {

    // Creating a new heap with the size 8.
    DSAHeap heap = new DSAHeap(8);

    // Adding elemnts to the heap in random order.
    heap.add(1, "Apple");
    heap.add(3, "Mango");
    heap.add(2, "Papaw");
    heap.add(4, "Orange");
    heap.add(8, "Cherries");
    heap.add(6, "Avacado");
    heap.add(7, "Banana");
    heap.add(5, "Pineapple");
    // heap.add(9, "rose apple");

    // Displaying the heap according to how array is built.
    heap.displayHeap();
    System.out.println();
    heap.heapSort();
    // Sorting the heap.
    // heap.heapSort();
    heap.heapSort();
    // Displaying the new sorted heap.
    System.out.println(" The heap in sorted order");
    heap.displayHeap();
    System.out.println(); // To print a new line.

    System.out.println(" Checking the remove method "); // Removes the data according to the highest priority.
    for (int i = 0; i < 8; i++) {
      System.out.println(heap.remove());
    }

  }

  public static void readLargeDataFile() {
    // Reading the CSV file.
    DSAHeap newentry = new DSAHeap(100);
    FileInputStream fileStream = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;
    int lineNum;
    String line;
    try {
      fileStream = new FileInputStream("RandomNames.csv"); // Reading the data in the csv to the heap.

      rdr = new InputStreamReader(fileStream);
      bufRdr = new BufferedReader(rdr);
      lineNum = 0;

      // bufRdr.readLine();
      line = bufRdr.readLine();

      while (line != null) {
        lineNum++;
        String[] splitLine;
        splitLine = line.split(",");

        {

          newentry.add(Integer.parseInt(splitLine[0]), splitLine[1]);

        }

        line = bufRdr.readLine();
      }
      fileStream.close();

    } catch (IOException errorDetails) {
      if (fileStream != null) {
        try {
          fileStream.close();
        } catch (IOException ex2) {
        }
      }
      System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
      System.out.println("Sorry we could not find the file you are reffering to");
    }

    newentry.displayHeap();
    System.out.println(newentry.remove());

  }

}
