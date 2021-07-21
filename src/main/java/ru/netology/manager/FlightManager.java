package ru.netology.manager;

import ru.netology.domain.Flight;
import ru.netology.repository.FlightRepository;


public class FlightManager {
    private FlightRepository repository;

    public FlightManager(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightRepository getRepository() {
        return repository;
    }

    public void setRepository(FlightRepository repository) {
        this.repository = repository;
    }

    public Flight[] searchBy(String from, String to) {
        Flight[] result = new Flight[0];
        for (Flight flight : repository.findAll()) {
            if (matches(flight, from, to)) {
                Flight[] tmp = new Flight[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = flight;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Flight flight, String from, String to) {
        if (flight.getFrom().contains(from) && flight.getTo().contains(to)) {
            return true;
        }
        return false;
    }
}
