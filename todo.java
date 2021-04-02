/**
 * TODO: 
 * 		Rotas
 * 
 *  1. correiosDto		GET 	/													| consultar na API do correios um determinado CEP (ou outra API similar)
 * 	
 *  2. userDto 			POST 	/usrs								[requestBody]	| cadastra usuários com informações básicas
 *  3. userDto 			GET 	/usrs/{id}											| resgata dados básicos do usuário
 *  4. contatoDto 		PUT 	/usrs/{id}/contato									| atualiza contato do usuário do id
 *  5. enderecoDto		PUT 	/usrs/{id}/endereco?cep={cep}						| atualiza o endereço base do usuário id e o CEP (utilizando a consulta na api do correios desenvolvido no item 1)
 *  6. enderecoDto 		PUT		/usrs/{id}/endereco					[requestBody]	| atualiza o complemento do endereco do usuario id
 *  7. servicoDto		POST 	/usrs/{id}/servico					[requestBody]	| cadastra um serviço de usuario profissional
 *  8. pedidoDto		POST	/usrs/{id}/pedido					[requestBody]	| cadastra um pedido solicitado pelo usuário id
 *  9. pedidoDto		PUT		/usrs/{id}/pedido					[requestBody]	| atualiza as disponibilidade de horários para efetuar o serviço / OBSERVAÇÃO: para que o usuário (cliente) seja avisado, será necessário incluir uma flag no userDto contendo: 1) idPedido atualizado; (precisa ser uma lista)
 * 10. pedidoDto		PUT		/usrs/{id}/pedido									| atualizar confirmação do usuário 		/ OBSERVAÇÃO: exige uma verificação se o usuário fez o pedido ou se oferece serviço, para confirmar finalização do usuário correto
 * 11. pedidoDto		PUT		/usrs/{id}/pedido/{id}?av={0 a 5}					| atualiza avaliação do usuário final 	/ OBSERVAÇÃO: exige uma verificação se o usuário fez o pedido ou se oferece serviço, para confirmar finalização do usuário correto
 * 12. 					DEL		/usrs/{id}/servico?stts={true ou false}				| atualiza status operacional de um serviço para inativo / OBSERVAÇÃO: não deve funcionar como um interruptor. Apenas inativar.
 * 13. servicoDto		GET		/usrs/{id}/servicos									| retornar uma lista dos serviços ATIVOS
 * 14. pedidosDto		GET		/usrs/{id}/pedidos									| retornar uma lista dos pedidos de um usuário id 
 * 15. pedidosDto		GET		/usrs/{id}/pedidos?atvs={true ou flase}				| retornar uma lista dos pedidos ativos ou inativos
 * 16. pedidoDto		GET		/usrs/{id}/pedidos/{id}								| retornar um objeto  
 * 17. enderecoDto		GET 	/usrs/{id}/endereco									| retornar um objeto dto com endereço do usuário id
 * 18. contatoDto		GET		/usrs/{id}/contato									| retornar um objeto dto com contato do usuário id
 *
 */
public class todo() {
	
	
	{SERVIDOR}/[INTERMEDIARIO DA API]/PATH
	
	HTTPS://LOCALHOST:8080/api/v1/customerview/cliente/id
 	
	
	GET 	/USER 		LIST TDOSO USUARIOS
	GET 	/USER/ID	O USUARIO DO ID
	GET		/USER/NEW	TRAS FORMULARIO PARA CADASTRO
	POST 	/USER		CADASTRA UM USUARIO 
	PUT		/USER/EDIT	TRAS FORMULARIO PARA EDIÇÃO
	PUT 	/USER/ID	ATUALIZA O USUARIO DO ID
	DELETE	/USER/ID	DELETA O USUARIO

	GET 	/USER/ID/CONTATO 		LIST TDOSO USUARIOS
	GET 	/USER/ID/CONTATO/ID		1 CONTATO ESPECIFICO DO USUARIO
	GET		/USER/NEW				TRAS FORMULARIO PARA CADASTRO
	POST 	/USER					CADASTRA UM USUARIO 
	PUT		/USER/EDIT				TRAS FORMULARIO PARA EDIÇÃO
	PUT 	/USER/ID				ATUALIZA O USUARIO DO ID
	DELETE	/USER/ID				DELETA O USUARIO
	
	
	
	
}