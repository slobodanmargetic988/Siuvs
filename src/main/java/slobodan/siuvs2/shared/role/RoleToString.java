package slobodan.siuvs2.shared.role;

import slobodan.siuvs2.model.Roles;

public class RoleToString {

    public static String forClient(Roles role) {
        String result;
        switch (role) {
            case CLIENT: result = "Унос/Измена"; break;
            case CLIENT_READ_ONLY: result = "Преглед"; break;
            default: result = ""; break;
        }
        return result;
    }

    public static String forAdmin(Roles role) {
        String result;
        switch (role) {
            case ADMIN: result = "Админ"; break;
            case RIS: result = "РИС"; break;
            case CLIENT: result = "Клијент - Унос/Измена"; break;
            case CLIENT_READ_ONLY: result = "Клијент - Преглед"; break;
            case MUP: result = "МУП"; break;
            case DISTRIKT: result = "Окружни супервизор"; break;
            case PROVINCE: result = "Регионални супервизор"; break;
            default: result = ""; break;
        }
        return result;
    }

}