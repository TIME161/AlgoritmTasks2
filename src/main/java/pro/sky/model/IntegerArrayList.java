package pro.sky.model;


import pro.sky.exceptions.AddNullException;
import pro.sky.exceptions.IncorrectIndexException;
import pro.sky.exceptions.WrongCapacityException;
import pro.sky.interfaces.IntegerList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class IntegerArrayList implements IntegerList {

    private static final int INITIAL_CAPACITY = 1;
    private Integer[] elements;
    private int size;

    public IntegerArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new WrongCapacityException("Вместимость " + initialCapacity + " некорректа!");
        }
        elements = new Integer[initialCapacity];
        size = 0;
    }

    public IntegerArrayList() {
        this(INITIAL_CAPACITY);
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new AddNullException("Вы добавили null");
        }
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = item;
        size++;
        alignmentCapacity();
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > size) {
            throw new IncorrectIndexException("Индекс " + index + " некорректен!");
        }
        if (size == elements.length) {
            expandCapacity();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = item;
        size++;
        alignmentCapacity();
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException("Индекс " + index + " некорректен!");
        }
        elements[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new IncorrectIndexException("Данного значения нет");
        }
        Integer removedItem = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        alignmentCapacity();
        return removedItem;
    }

    @Override
    public Integer removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException("Индекс " + index + " некорректен!");
        }
        Integer removedItem = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        alignmentCapacity();
        return removedItem;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException("Индекс " + index + " некорректен!");
        }
        return elements[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new AddNullException("Вы добавили null");
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elements[i] == null ? otherList.get(i) != null : !elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        alignmentCapacity();
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    private void expandCapacity() {
        int newCapacity = elements.length * 2;
        Integer[] newElements = new Integer[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void alignmentCapacity() {
        int newCapacity = size;
        Integer[] newElements = new Integer[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public String toString() {
        return "StringArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    public static Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static int binarySearch(Integer[] arr, int key) {
        sortInsertion(arr);
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}