import java.math.BigDecimal;

public class Toetsenbord extends Computeronderdeel {
    //*****Attribuut***
    private boolean draadloos;
    //*****Constructors***
    public Toetsenbord() {
        super();
        draadloos = false;
    }

    public Toetsenbord(String merk, BigDecimal prijs) {
        super(merk);
        this.prijs = prijs;
        draadloos = false;
    }

    //***Getter en setter***
    public boolean isDraadloos() {
        return draadloos;
    }

    public void setDraadloos(boolean draadloos) {
        this.draadloos = draadloos;
    }

    //**toString()***
    @Override
    public String toString() {
        String tekst;

        tekst = super.toString();
        tekst += "Draadloos: " + draadloos + "\n";
        return (tekst);
    }
}
