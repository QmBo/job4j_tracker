package ru.job4j.tracker;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindItemByNameNotFoundThenReturnEmptyArray() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        Item secondItem = new Item("test2", "testDescription2", 1234L);
        Item thirdItem = new Item("test1", "testDescription3", 12345L);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        ArrayList<Item> result = tracker.findByName("test");
        assertThat(result, is(new ArrayList<>()));
    }

    @Test
    public void whenDeleteItemThenTrackerReturnArrayWithoutItem() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        Item secondItem = new Item("test2", "testDescription2", 1234L);
        Item thirdItem = new Item("test3", "testDescription3", 12345L);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        tracker.delete(secondItem.getId());
        assertThat(tracker.getAll(), is(new ArrayList<>(Arrays.asList(firstItem, thirdItem))));
    }

    @Test
    public void whenFindItemByNameThenTrackerReturnArrayItemsWithName() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        Item secondItem = new Item("test2", "testDescription2", 1234L);
        Item thirdItem = new Item("test1", "testDescription3", 12345L);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        ArrayList<Item> result = tracker.findByName("test1");
        assertThat(result, is(new ArrayList<>(Arrays.asList(firstItem, thirdItem))));
    }
}