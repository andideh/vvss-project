import controller.ClientController;
import model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by AndiD on 3/13/18.
 */


public class AddClientControllerTest {

    private ClientController controller;

    @Before
    public void init() {
        controller = new ClientController();
    }

    @Test
    public void testAddClientValid() {
        Random rnd = new Random();
        // Given
        String name = "Andrei";
        String address = "Avram Iancu";
        int nr = rnd.nextInt(50) + 1;
        String id = String.valueOf(nr);

        // When
        String result = controller.AddClient(name, address, id);

        // Then
        Assert.assertNull(result);
    }

    @Test
    public void testAddClientInvalidName() {
        Random rnd = new Random();
        // Given
        String name = "An4";
        String address = "Avram Iancu";
        int nr = rnd.nextInt(50) + 1;
        String id = String.valueOf(nr);

        // When
        String result = controller.AddClient(name, address, id);

        // Then
        Assert.assertNotNull(result);
    }
}
