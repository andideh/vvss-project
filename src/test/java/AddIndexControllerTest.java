import controller.ClientController;
import model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by AndiD on 3/27/18.
 */


public class AddIndexControllerTest {

    private ClientController controller;
    private String clientId = "756";
    private String clientName = "Andrei";
    private String clientAddress = "Avram Iancu";
    private Client client = new Client(clientName, clientAddress, clientId);

    @Before
    public void init() {
        controller = new ClientController();
    }

    @Test
    public void test_InvalidMonth() {
        int year = 2016;
        int month = 20;
        float toPay = 1;

        Assert.assertNotNull(controller.AddClientIndex(client, year, month, toPay));
    }

    @Test
    public void test_NegativeToPay() {
        int year = 2018;
        int month = 10;
        float toPay = -3;

        Assert.assertNotNull(controller.AddClientIndex(client, year, month, toPay));
    }

    @Test
    public void test_InvalidYear() {
        int year = 1990;
        int month = 10;
        float toPay = 300;

        Assert.assertNotNull(controller.AddClientIndex(client, year, month, toPay));
    }

    @Test
    public void test_invalidClient() {
        String clientId = "";
        String clientName = "Aaa";
        String address = "No-address";
        Client client = new Client(clientName, clientAddress, clientId);

        int year = 2018;
        int month = 10;
        float toPay = 300;


        Assert.assertNotNull(controller.AddClientIndex(client, year, month, toPay));
    }

    @Test
    public void test_inexistentClient() {
        String clientId = "111";
        String clientName = "Aaa";
        String address = "No-address";
        Client client = new Client(clientName, clientAddress, clientId);

        int year = 2018;
        int month = 10;
        float toPay = 300;
        
        Assert.assertNotNull(controller.AddClientIndex(client, year, month, toPay));
    }
}
