package testes;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import util.JPAUtil;
import vo.RelatorioDeVendaVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		
		
		
		em.getTransaction().commit();
		
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
		
		List<RelatorioDeVendaVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Iphone", "Muito Legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Play station 5", new BigDecimal("1000"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook pro", new BigDecimal("5000"), informatica);
		
		
		Cliente cliente = new Cliente("Rodigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		
		categoriaDao.cadastrar(videogames);
		produtoDao.cadastrar(videogame);
		clienteDao.cadastrar(cliente);
		
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(macbook);
		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}

}
