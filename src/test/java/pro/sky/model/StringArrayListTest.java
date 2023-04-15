package pro.sky.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.exceptions.AddNullException;
import pro.sky.exceptions.IncorrectIndexException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StringArrayListTest {

    IntegerArrayList list = new IntegerArrayList();

    private static Stream<Arguments> words() {
        return Stream.of(
                Arguments.of(3, 0, 7, 8),
                Arguments.of(4, 0, 7, 8),
                Arguments.of(5, 0, 7, 8)
        );
    }


    @ParameterizedTest(name = "{index} => addWord={0}")
    @MethodSource("words")
    public void testAdd(Integer addWord) {
        Integer actualResult = list.add(addWord);
        assertEquals(addWord, actualResult);

    }

    @ParameterizedTest(name = "{index} => addWord={0}, index={1}")
    @MethodSource("words")
    public void testAddIndex(Integer addWord, int index) {
        Integer actualResult = list.add(index, addWord);
        assertEquals(addWord, actualResult);
    }

    @ParameterizedTest(name = "{index} => newWord={0}, index={1}, wordOne={2}, wordTwo={3}")
    @MethodSource("words")
    public void testSet(Integer newWord, int index, Integer wordOne, Integer wordTwo) {
        list.add(wordOne);
        list.add(wordTwo);
        Integer actualResult = list.set(index, newWord);
        Integer expectedResult = list.get(index);


        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        Integer actualResult = list.remove(2);
        assertEquals(2, actualResult);

    }

    @Test
    public void testIndexRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        Integer actualResult = list.removeByIndex(1);
        assertEquals(2, actualResult);
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        list.add(3);
        Boolean expectedResult = true;
        Boolean actualResult = list.contains(3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIndexOf() {
         list.add(1);
        list.add(2);
        list.add(3);
        int expectedResult = 2;
        int actualResult = list.indexOf(3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testLastIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        int expectedResult = 3;
        int actualResult = list.lastIndexOf(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);
        Integer expectedResult = 1;
        Integer actualResult = list.get(0);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testTestEquals() {

        list.add(1);
        list.add(2);
        list.add(3);
        IntegerArrayList list1 = new IntegerArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        Boolean expectedResult = true;
        Boolean actualResult = list.equals(list1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSize() {
       list.add(1);
        list.add(2);
        list.add(3);
        int expectedResult = 3;
        int actualResult = list.size();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIsEmpty() {
        Boolean expectedResult = true;
        Boolean actualResult = list.isEmpty();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testClear() {
       list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        Boolean expectedResult = true;
        Boolean actualResult = list.isEmpty();
        assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] actualResult = list.toArray();
        assertEquals(actualResult.length, 3);
        assertEquals(actualResult[0], 1);
        assertEquals(actualResult[1], 2);
        assertEquals(actualResult[2], 3);
    }

    @Test
    public void testTestToString() {
        list.add(1);
        list.add(2);
        list.add(3);
        String expectedResult = "StringArrayList{elements=[1, 2, 3], size=3}";
        String actualResult = list.toString();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void addWithException() {
        Exception exception = assertThrows(AddNullException.class, () -> list.add(null));
        String expectedMessege = "Вы добавили null";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void addIndexWithException() {
        int indexLocal = 10;
        Exception exception = assertThrows(IncorrectIndexException.class, () -> list.add(indexLocal,1));
        String expectedMessege = "Индекс " + indexLocal + " некорректен!";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void setWithException() {
        int indexLocal = 10;
        Exception exception = assertThrows(IncorrectIndexException.class, () -> list.set(indexLocal,1));
        String expectedMessege = "Индекс " + indexLocal + " некорректен!";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void removeWithException() {
        list.add(1);
        list.add(2);
        list.add(3);

        Exception exception = assertThrows(IncorrectIndexException.class, () -> list.remove(4));
        String expectedMessege = "Данного значения нет";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void removeIndexWithException() {
        list.add(1);
        list.add(2);
        list.add(3);
        int indexLocal = 4;
        Exception exception = assertThrows(IncorrectIndexException.class, () -> list.removeByIndex(indexLocal));
        String expectedMessege = "Индекс " + indexLocal + " некорректен!";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void getWithException() {
        list.add(1);
        list.add(2);
        list.add(3);
        int indexLocal = 4;
        Exception exception = assertThrows(IncorrectIndexException.class, () -> list.get(indexLocal));
        String expectedMessege = "Индекс " + indexLocal + " некорректен!";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    public void equalsWithException() {
        Exception exception = assertThrows(AddNullException.class, () -> list.equals(null));
        String expectedMessege = "Вы добавили null";
        assertEquals(expectedMessege, exception.getMessage());
    }

}