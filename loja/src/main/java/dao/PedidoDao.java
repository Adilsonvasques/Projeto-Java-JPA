package dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import modelo.Categoria;
import modelo.Pedido;
import vo.RelatorioDeVendaVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
		
	public BigDecimal valorTotalVendido(){
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioDeVendaVo> relatorioDeVendas(){
		String jpql = "SELECT new vo.RelatorioDeVendaVo( "	
					+ "produto.nome, "
					+ "SUM(item.quantidade), "	
					+ "MAX(pedido.data)) "	
					+ "FROM Pedido pedido "	
					+ "JOIN pedido.itens item "	
					+ "JOIN item.produto produto "	
					+ "GROUP BY produto.nome "	
					+ "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql, RelatorioDeVendaVo.class).getResultList();
	}
} 
