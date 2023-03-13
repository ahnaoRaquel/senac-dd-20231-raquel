package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class EnderecoDAO {
	
	//Insert
	//INSERT INTO ENDERECO(RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO)
	//VALUES('','','','','SC','');
	
	/**
	 * Insere um novo telefone no banco
	 * @param novoTelefone o telefone a ser persistido
	 * @return o telefone inserido com a chave prim�ria gerada
	 */
	
	public Endereco inserir(Endereco novoEndereco) {
		//Conectar ao banco
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO) "
				+ " VALUES (?, ?, ?, ?, ?, ?) ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		
		//Executar o Insert
		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getCep());
			query.setString(3, novoEndereco.getBairro());
			query.setString(4, novoEndereco.getCidade());
			query.setString(5, novoEndereco.getEstado());
			query.setString(6, novoEndereco.getNumero());
			query.execute();
			
			//Preencher o id gerado no banco no objeto
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endere�o. \nCausa: " + e.getMessage());
		} finally {
			//Fechar conex�o
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return novoEndereco;
	}
	
	

}
