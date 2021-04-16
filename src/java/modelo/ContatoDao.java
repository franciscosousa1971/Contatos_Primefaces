package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rosicléia Frasson
 */
public class ContatoDao {

	private final String INSERT = "INSERT INTO contatos (NOME, TELEFONE, EMAIL) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE contatos SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM contatos WHERE ID =?";
	private final String LIST = "SELECT * FROM contatos";
	private final String LISTBYID = "SELECT * FROM contatos WHERE ID=?";

	public void inserir(Contato contato) throws SQLException, ClassNotFoundException {
		if (contato != null) {
			Connection conn = null;
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, contato.getNome());
				pstm.setString(2, contato.getEmail());
                                pstm.setString(3, contato.getTelefone());

				pstm.execute();
				FabricaConexao.fechaConexao(conn, pstm);

		}
	}

	public void atualizar(Contato contato) throws SQLException, ClassNotFoundException {
		if (contato != null) {
			Connection conn = null;
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);

				pstm.setString(1, contato.getNome());
				pstm.setInt(2, contato.getId());
				pstm.setString(3, contato.getEmail());
				pstm.setString(4, contato.getTelefone());

				pstm.execute();

                }
	}
	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conn = null;
			conn = FabricaConexao.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(DELETE);

			pstm.setInt(1, id);

			pstm.execute();
			FabricaConexao.fechaConexao(conn, pstm);

	}

	public List<Contato> getContatos() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Contato> contatos = new ArrayList<Contato>();
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Contato contato = new Contato();

				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
                                contato.setTelefone(rs.getString("telefone"));
				contatos.add(contato);
		}
		return contatos;
	}

	public Contato getContatoById(int id) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Contato contato = new Contato();
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
                                contato.setTelefone(rs.getString("telefone"));
			}
		return contato;
	}
}