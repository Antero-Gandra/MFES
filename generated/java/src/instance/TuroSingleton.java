package instance;

import vdm.Turo;

public class TuroSingleton {
    private static Turo turoInstance;

    public static Turo getTuroInstance() {
        if (turoInstance == null)
            turoInstance = TuroFactory.getTuro();
        return turoInstance;
    }
}
