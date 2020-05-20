package slobodan.siuvs2.shared;

public class AssesmentHelper1 {

    public static final String Zemljotres = "земљотреса";
    public static final String Odron = "одрона, клизишта и ерозије";
    public static final String Poplava = "поплаве";
    public static final String Grad = "града";
    public static final String Oluja = "олујног ветра";
    public static final String Toplota = "топлог таласа";
    public static final String Susa = "суше";
    public static final String Mecava = "снежне мећаве, наноса и поледица, хладног таласа";
    public static final String Voda = "недостака воде за пиће";
    public static final String Epidemija = "епидемије и пандемије";
    public static final String BolestZ = "болести животиња";
    public static final String BolestB = "болести биљака";
    public static final String Pozari = "пожара, експлозија и пожара на отвореном";
    public static final String Tehnicke = "техничко-технолошке несреће";

    public static String getOpasnost(int pageId) {

        switch (pageId) {
            case 16:
            case 17:
                return Zemljotres;
            case 32:
            case 33:
                return Odron;
            case 37:
            case 38:
                return Poplava;
            case 42:
            case 43:
                return Grad;
            case 47:
            case 48:
                return Oluja;
            case 83:
            case 84:
                return Toplota;
            case 52:
            case 53:
                return Susa;
            case 57:
            case 58:
                return Mecava;
            case 62:
            case 63:
                return Voda;
            case 67:
            case 68:
                return Epidemija;
            case 85:
            case 86:
                return BolestZ;
            case 87:
            case 88:
                return BolestB;
            case 89:
            case 90:
                return Pozari;
            case 91:
            case 92:
                return Tehnicke;

            default:
                return "";
        }

    }

    public AssesmentHelper1() {
    }

}
