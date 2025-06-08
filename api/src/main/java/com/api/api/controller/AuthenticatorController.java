package com.api.api.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticatorController {
    
    public static boolean verifyValidEmail(String correo) {
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Hey");
            throw new IllegalStateException("El email no cumple con las condiciones");
        }
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean verifyValidPassword(String password) {
        if (password.length() < 6){ 
            throw new IllegalStateException("La contraseña no cumple con las condiciones");
        } else {
            return true;
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash de contraseña.", e);
        }
    }
}
