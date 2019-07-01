package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessPiece;
import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;

public class PieceDAO {
	private static final String INSERT_PIECE =
			"insert into piece(player, piece_type, x_position, y_position, room_number) values(?,?,?,?,?)";
	private static final String SELECT_PIECES_QUERY =
			"select player, piece_type, x_position, y_position from piece where room_number = ?";
	private static final String DELETE_ALL_PIECES_QUERY =
			"delete from piece where room_number = ?";

	public void addAllPieces(int roomNumber, List<Piece> pieces) throws SQLException {
		for (Piece piece : pieces) {
			addPiece(roomNumber, piece);
		}
	}

	public void addPiece(int roomNumber, Piece piece) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(INSERT_PIECE, piece.getPlayer().name(), piece.getChessType(),
				piece.getCoordinateX(), piece.getCoordinateY(), roomNumber);
	}

	public void deleteAllPieces(int roomNumber) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(DELETE_ALL_PIECES_QUERY, roomNumber);
	}

	public List<Piece> getChessPieces(int roomNumber) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParams(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomNumber);
			}
		};

		RowMapper<Piece> rm = new RowMapper<Piece>() {
			@Override
			public Piece mapRow(ResultSet rs) throws SQLException {
				Player player = Player.valueOf(rs.getString(1));
				Type type = Type.valueOf(rs.getString(2));
				Position position = Position.getPosition(rs.getInt(3), rs.getInt(4));
				return ChessPiece.generatePiece(player, type, position);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.executeQueryForMultiple(SELECT_PIECES_QUERY, rm, pss);
	}
}
