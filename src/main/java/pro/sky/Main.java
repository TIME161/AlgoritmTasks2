package pro.sky;

import pro.sky.model.IntegerArrayList;

public class Main {
    public static void main(String[] args) {
        IntegerArrayList list = new IntegerArrayList();

        list.add(26);
        list.add(27);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        System.out.println(list);

        list.add(0, 15);
        list.add(2, 16);

        System.out.println(list);
        line();

        list.set(2, 22);
        list.set(3, 33);

        System.out.println(list);
        line();

        list.remove(26);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);
        line();

        System.out.println(list.contains(33));
        line();

        System.out.println(list.indexOf(2));
        line();

        System.out.println(list.lastIndexOf(2));
        line();

        System.out.println(list.get(4));
        line();

        IntegerArrayList listOne = new IntegerArrayList();
        IntegerArrayList listTwo = new IntegerArrayList();
        listTwo.add(26);
        listTwo.add(27);
        listTwo.add(1);
        listTwo.add(2);
        listTwo.add(3);
        listTwo.add(2);
        listTwo.add(0, 15);
        listTwo.add(2, 16);
        listTwo.set(2, 22);
        listTwo.set(3, 33);
        listTwo.remove(26);
        listTwo.remove(1);

        System.out.println(list.equals(listOne));
        System.out.println(list.equals(listTwo));
        line();

        System.out.println(list.size());
        line();

        Integer[] text = list.toArray();
        System.out.println(text[0] +" "+ text[1] +" "+ text[2] +" "+ text[3] +" "+ text[4] +" "+ text[5]);
        line();

        list.clear();
        System.out.println(list);
        line();

        System.out.println(list.isEmpty());
        line();
    }

    private static void line() {
        System.out.println("---------------------------------------------");
    }
}