import java.io.*;
import java.util.*;

public class OrderedListDemo {
    public static void main(String[] args) throws IOException {

        //Scanner object to read inputs and files.
        Scanner kb = new Scanner(System.in);

        System.out.println("Enter the first filename to read from: ");
        String file1 = kb.nextLine();
        Scanner firstFile = new Scanner(new File(file1));

        System.out.println("Enter the second filename to read from: ");
        String file2 = kb.nextLine();
        Scanner secondFile = new Scanner(new File(file2));

        //OrderedList object to store data from the file1.
        OrderedList<String> first = new OrderedList<>();

        //Storing data from the file into an ordered list.
        while (firstFile.hasNextLine()){
            String input = firstFile.nextLine();
            first.insert(input);
        }

        //OrderedList object to store data from the file2.
        OrderedList<String> second = new OrderedList<>();

        //Storing data from the file into an ordered list.
        while (secondFile.hasNextLine()){
            String input = secondFile.nextLine();
            second.insert(input);
        }


        //Converting method output to string to write in a file.
        StringBuilder mergedList = new StringBuilder();
        for (int i=0; i<merge(first, second).size(); i++) {
            mergedList.append(merge(first, second).get(i)).append("\n");
        }
        FileWriter merged = new FileWriter("merged.txt");
        merged.write(String.valueOf(mergedList));
        merged.close();

        //Converting method output to string to write in a file.
        StringBuilder differenceList = new StringBuilder();
        for (int i=0; i<difference(first, second).size(); i++) {
            differenceList.append(difference(first, second).get(i)).append("\n");
        }
        FileWriter difference = new FileWriter("difference.txt");
        difference.write(String.valueOf(differenceList));
        difference.close();

        //Converting method output to string to write in a file.
        StringBuilder commonList = new StringBuilder();
        for (int i=0; i<common(first, second).size(); i++) {
            commonList.append(common(first, second).get(i)).append("\n");
        }
        FileWriter common = new FileWriter("common.txt");
        common.write(String.valueOf(commonList));
        common.close();

    }

    /**
     * The purpose of this method to read in two lists and merge them using the two finger algorithm.
     *
     * @param list1 to input a list.
     * @param list2 to input a list.
     * @return
     * @param <T> an ordered list which is result of merging file1 and file2.
     */
    public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1, OrderedList<T> list2){

        int f1 = 0;
        int f2 = 0;
        OrderedList<T> list3 = new OrderedList<>();

        while (f1 < list1.size() && f2 < list2.size()) {
            if ((list1.get(f1).compareTo(list2.get(f2)))<0) {
                list3.add(list1.get(f1));
                f1++;
            } else if ((list2.get(f2).compareTo(list1.get(f1)))<0) {
                list3.add(list2.get(f2));
                f2++;
            }
            else {
                list3.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        if (f1 == list1.size()) {
            for (int i=f2; i< list2.size(); i++) {
                list3.add(list2.get(i));
            }

        }
        if (f2 == list2.size()) {
            for (int i=f1; i< list1.size(); i++) {
                list3.add(list1.get(i));
            }
        }
        return list3;
    }

    /**
     *The purpose of this method is to read in two lists and return elements from list1 that are not in list2.
     *
     * @param list1 to input a list.
     * @param list2 to input a list.
     * @return
     * @param <T> an ordered list which is difference of the two list.
     */
    public static <T extends Comparable<T>> OrderedList<T> difference(OrderedList<T> list1, OrderedList<T> list2){

        int f1 = 0;
        int f2 = 0;
        OrderedList<T> list3 = new OrderedList<>();

        while (f1 < list1.size() &&  f2 < list2.size()) {
            if ((list1.get(f1).compareTo(list2.get(f2))) < 0) {
                list3.add(list1.get(f1));
                f1++;
            } else if ((list2.get(f2).compareTo(list1.get(f1))) < 0) {
                f2++;
            } else {
                f1++;
                f2++;
            }
        }

        if (list1.size() > list2.size()) {
            for (int i=f1; i< list1.size(); i++) {
                list3.add(list1.get(i));
            }
        }

        return list3;

    }

    /**
     * The purpose of this method is to read in two lists and return a list containing common elements from the two lists.
     * @param list1 to input a list.
     * @param list2 to input a list.
     * @return
     * @param <T> an ordered list containing common elements from both the files.
     */
    public static <T extends Comparable<T>> OrderedList<T> common(OrderedList<T> list1, OrderedList<T> list2){

        int f1 = 0;
        int f2 = 0;
        OrderedList<T> list3 = new OrderedList<>();

        while (f1 < list1.size() && f2 < list2.size()) {
            if ((list1.get(f1).compareTo(list2.get(f2))) < 0) {
                f1++;
            } else if ((list2.get(f2).compareTo(list1.get(f1))) < 0) {
                f2++;
            } else {
                list3.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        return list3;

    }
}
