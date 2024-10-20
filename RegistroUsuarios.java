package Grupo4SC303MNProyectoClienteServidor;

public class RegistroUsuarios {

    public static boolean registerUser(String name, String email, String password) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password); // Asegúrate de encriptar la contraseña
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password); // Aquí también debería validarse la contraseña encriptada
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Si encuentra un resultado, el usuario existe y la contraseña es correcta

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
