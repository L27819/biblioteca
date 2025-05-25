package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.model.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDao {

    private final Connection connection;

    public PrestamoDao(Connection connection) {
        this.connection = connection;
    }

    public List<Prestamo> getByUsuarioId(int idUsuario) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setId_prestamo(rs.getInt("id_prestamo"));
                p.setId_libro(rs.getInt("id_libro"));
                p.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());

                Date fechaFinSql = rs.getDate("fecha_fin");
                if (fechaFinSql != null) {
                    p.setFecha_fin(fechaFinSql.toLocalDate());
                } else {
                    p.setFecha_fin(null);
                }

                p.setEstado(rs.getString("estado"));
                prestamos.add(p);
            }
        }

        return prestamos;
    }
}