import controller.ClientController;
import model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Random;

/**
 * Created by AndiD on 3/13/18.
 */


public class AddClientControllerTest {

    private ClientController controller;
    private String id;

    @Before
    public void init() {
        controller = new ClientController();
        Random rnd = new Random();
        int nr = rnd.nextInt(1000) + 1;
        id = String.valueOf(nr);

    }

    @Test
    public void testAddClientValid() {
        // Given
        String name = "Andrei";
        String address = "Avram Iancu";

        // When
        String result = controller.AddClient(name, address, id);

        // Then
        Assert.assertNull(result);
    }

    @Test
    public void testAddClientInvalidName() {
        // Given
        String name = "An4";
        String address = "Avram Iancu";

        // When
        String result = controller.AddClient(name, address, id);

        // Then
        Assert.assertNotNull(result);
    }

    @Test
    public void testInvalidNameLength() {
        String name = String.join("", Collections.nCopies(256, "a"));
        String address = "Avram Iancu";

        String result = controller.AddClient(name, address, id);

        Assert.assertNotNull(result);
    }

    @Test
    public void testEmptyAddress() {
        String name = "Test";
        String address = "";

        Assert.assertNotNull(controller.AddClient(name, address, id));
    }

    @Test
    public void testEmptyName() {
        String name = "";
        String address = "aaa";

        Assert.assertNotNull(controller.AddClient(name, address, id));
    }

    @Test
    public void testInvalidIdFormat() {
        String name = "Andrei Dehelean";
        String address = "Avram Iancu";
        String invalidId = "abc";

        Assert.assertNotNull(controller.AddClient(name, address, invalidId));
    }

    @Test
    public void testEmptyId() {
        String name = "Andrei Dehelean";
        String address = "Avram Iancu";
        String emptyId = "";

        Assert.assertNotNull(controller.AddClient(name, address, emptyId));
    }

}
