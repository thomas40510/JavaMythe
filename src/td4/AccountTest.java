package td4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account c1 = new Account("J. DUPONT", 1000);
    Account c2 = new Account("C. DURANT", 50000,
            6000, 5000);


    @Test
    public void testCreateAccount(){
        assert(c1 != null);
        assert(c2 != null);
    }

    @Test
    public void testInformations(){
        assertEquals("J. DUPONT", c1.getName());
        assertEquals(1000, c1.getBalance());
        assertEquals(1000, c1.getMaxWithdrawal());
        assertEquals(0, c1.getMaxDeficit());

        assertEquals("C. DURANT", c2.getName());
        assertEquals(50000, c2.getBalance());
        assertEquals(6000, c2.getMaxWithdrawal());
        assertEquals(5000, c2.getMaxDeficit());
    }

    @Test
    public void testWithdraw(){
        assertTrue(c1.withdraw(300));
        assertEquals(700, c1.getBalance());

        assertTrue(c2.withdraw(600));
        assertEquals(49400, c2.getBalance());
    }

    @Test
    public void testDeposit(){
        c1.deposit(500);
        assertEquals(1500, c1.getBalance());

        c2.deposit(1000);
        assertEquals(51000, c2.getBalance());
    }

    @Test
    public void testDisplay(){
        String acc1 = c1.toString();
        String acc2 = c2.toString();
        assert(acc1.contains("J. DUPONT"));
        assert(acc1.contains("1000"));
        assert(acc1.contains("1000"));
        assert(acc1.contains("0"));
        assert(!acc1.contains("IS IN DEFICIT"));

        assert(acc2.contains("C. DURANT"));
        assert(acc2.contains("50000"));
        assert(acc2.contains("6000"));
        assert(acc2.contains("5000"));
        assert(!acc2.contains("IS IN DEFICIT"));
    }

    @Test
    public void testTransfer(){
        c1.setBalance(1000);
        c2.setBalance(50000);
        assertTrue(c2.transfer(c1, 1000));
        assertEquals(2000, c1.getBalance());
        assertEquals(49000, c2.getBalance());
    }

    @Test
    public void testMaxWithdrawal(){
        c1.setBalance(1000);
        c2.setBalance(50000);
        assertFalse(c1.withdraw(2000));
        assertEquals(1000, c1.getBalance());

        assertFalse(c2.withdraw(7000));
        assertEquals(50000, c2.getBalance());
    }

    @Test
    public void testMaxDeficit(){
        c1.setBalance(-c1.getMaxDeficit());
        c2.setBalance(-c2.getMaxDeficit());
        assertFalse(c1.withdraw(10));
        assertEquals(-c1.getMaxDeficit(), c1.getBalance());

        assertFalse(c2.withdraw(10));
        assertEquals(-c2.getMaxDeficit(), c2.getBalance());
    }

    @Test
    public void testDeficit(){
        c1.setBalance(-10);
        c2.setBalance(-200);
        assert(c1.toString().contains("IS IN DEFICIT"));
        assert(c2.toString().contains("IS IN DEFICIT"));
    }

    @Test
    public void testMaxTransfer(){
        c1.setBalance(1000);
        c2.setBalance(50000);
        assertFalse(c2.transfer(c1, 7000));
        assertEquals(1000, c1.getBalance());
        assertEquals(50000, c2.getBalance());
    }

    @Test
    public void testMaxDeficitTransfer(){
        c1.setBalance(1000);
        c2.setBalance(-c2.getMaxDeficit());
        assertFalse(c2.transfer(c1, 10));
        assertEquals(1000, c1.getBalance());
        assertEquals(-c2.getMaxDeficit(), c2.getBalance());
    }

    @Test
    public void testDeficitTransfer(){
        c1.setBalance(1000);
        c2.setBalance(100);
        c2.transfer(c1, 500);
        assertEquals(1500, c1.getBalance());
        assertEquals(-400, c2.getBalance());
        assert(c2.toString().contains("IS IN DEFICIT"));
    }

    @Test
    public void testTransferToSelf(){
        assertTrue(c1.transfer(c1, 1000));
        assertEquals(1000, c1.getBalance());
    }

    @Test
    public void testTransferToNull(){
        c1.setBalance(1000);
        assertFalse(c1.transfer(null, 1000));
        assertEquals(1000, c1.getBalance());
    }
}