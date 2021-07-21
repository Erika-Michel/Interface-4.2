package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Flight;
import ru.netology.repository.FlightRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FlightManagerTest {
    private FlightRepository repository = new FlightRepository();
    private FlightManager manager = new FlightManager(repository);

    private Flight first = new Flight(1, 100_000, "JFK", "SVO", 630);
    private Flight second = new Flight(2, 11_000, "LED", "SVO", 150);
    private Flight third = new Flight(3, 20_000, "JFK", "PAC", 180);
    private Flight forth = new Flight(4, 35_000, "LED", "SVO", 150);
    private Flight fifth = new Flight(5, 70_000, "CDG", "JFK", 630);
    private Flight sixth = new Flight(6, 22_000, "FRA", "DME", 630);
    private Flight seventh = new Flight(7, 100_000, "JFK", "SVO", 630);
    private Flight eighth = new Flight(8, 18_000, "LED", "SVO", 150);

    @BeforeEach
    public void setting() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(eighth);
    }

    @Test
    public void shouldFindIfOne() {

        Flight[] expected = new Flight[]{sixth};
        Flight[] actual = manager.searchBy("FRA", "DME");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindIfTwoSamePriceAndSort() {

        Flight[] expected = new Flight[]{first, seventh};
        Flight[] actual = manager.searchBy("JFK", "SVO");

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindIfManyAndSort() {

        Flight[] expected = new Flight[]{second, eighth, forth};
        Flight[] actual = manager.searchBy("LED", "SVO");

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindIfNotExist() {

        Flight[] expected = new Flight[]{};
        Flight[] actual = manager.searchBy("PAC", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindIfFromAndToViceVersa() {

        Flight[] expected = new Flight[]{};
        Flight[] actual = manager.searchBy("SVO", "LED");

        assertArrayEquals(expected, actual);
    }

}