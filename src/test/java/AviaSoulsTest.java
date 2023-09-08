import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    @Test
    public void testSortTicketByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 13);
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 11);
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 19);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket4, ticket6, ticket1};
        Ticket[] actual = manager.search("Msk", "Spb");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortsIfNotFoundResult() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 13);
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 11);
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 19);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Minsk", "Kazan");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortsFoundOneTicket() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 13);
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 11);
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 19);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.search("LA", "Toronto");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortsByFlyTime() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 18);
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 12);
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 18);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket6, ticket4, ticket1};
        Ticket[] actual = manager.searchAndSortBy("Msk", "Spb", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortsByFlyTimeFoundOne() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 18); // 7
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 12); //3
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 18); //1
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket5};
        Ticket[] actual = manager.searchAndSortBy("Ufa", "Sochi", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortsByFlyTimeNotFound() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Msk", "Spb", 1000, 11, 18); // 7
        Ticket ticket2 = new Ticket("Spb", "Tokio", 500, 11, 13);
        Ticket ticket3 = new Ticket("LA", "Toronto", 500, 11, 13);
        Ticket ticket4 = new Ticket("Msk", "Spb", 300, 9, 12); //3
        Ticket ticket5 = new Ticket("Ufa", "Sochi", 500, 11, 13);
        Ticket ticket6 = new Ticket("Msk", "Spb", 500, 17, 18); //1
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("LA", "Msk", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
